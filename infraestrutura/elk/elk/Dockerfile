FROM sebp/elk

# overwrite existing file

# add or overwrite files
ADD /config/12-some-filter.conf /etc/logstash/conf.d/12-some-filter.conf
ADD /config/02-beats-input.conf /etc/logstash/conf.d/02-beats-input.conf
ADD /config/30-output.conf /etc/logstash/conf.d/30-output.conf

# Installing Elasticsearch plugins
ENV ES_HOME /opt/elasticsearch
WORKDIR ${ES_HOME}
RUN CONF_DIR=/etc/elasticsearch gosu elasticsearch bin/elasticsearch-plugin \
    install -b ingest-geoip

# Installing Logstash plugins
WORKDIR ${LOGSTASH_HOME}
RUN gosu logstash bin/logstash-plugin install logstash-input-rss

# Installing Kibana plugins
WORKDIR ${KIBANA_HOME}
RUN gosu kibana bin/kibana-plugin install elastic/sense
