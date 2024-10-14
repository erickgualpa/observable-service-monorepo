#!/bin/bash
GRAFANA_URL="http://$GRAFANA_USER:$GRAFANA_PASSWORD@grafana:3000"

DASHBOARD=$(cat etc/grafana/init/dashboard.json)
CREATE_DASHBOARD_REQUEST="{
  \"dashboard\":$DASHBOARD
}"

# echo $CREATE_DASHBOARD_REQUEST

curl -v -s -X POST "${GRAFANA_URL}/api/dashboards/db" \
  -H "Content-Type: application/json" \
  -d "$CREATE_DASHBOARD_REQUEST"