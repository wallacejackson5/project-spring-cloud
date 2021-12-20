# Python program to populate the wiremock
import subprocess
import requests
import time
import os

def loadStubMappings():
  with open("./scripts/wiremock-init-data.log") as fp:
    for line in fp:
      subprocess.run([line.strip()], shell=True, capture_output=True)

attempts = 0
while attempts < 10:
  try:
    requests.get("http://localhost:9090/__admin/recordings/status")
    loadStubMappings()
    os.system("echo \"$(tput setaf 10) ===== Wiremock Stab Mappings loaded successfully. ===== $(tput sgr0)\"")
    break
  except requests.ConnectionError:
    attempts+=1
    time.sleep(3)

if attempts == 10:
  os.system("echo \"$(tput setaf 9) ===== Wiremock Stab Mappings failed. ===== $(tput sgr0)\"")

