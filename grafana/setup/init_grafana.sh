#!/bin/bash
GRAFANA_URL="http://$GRAFANA_USER:$GRAFANA_PASSWORD@grafana:3000"

## LOAD DASHBOARD
DASHBOARD=$(cat etc/grafana/init/dashboard.json)
CREATE_DASHBOARD_REQUEST="{
  \"dashboard\":$DASHBOARD
}"

# echo $CREATE_DASHBOARD_REQUEST

curl -v -s -X POST "${GRAFANA_URL}/api/dashboards/db" \
  -H "Content-Type: application/json" \
  -d "$CREATE_DASHBOARD_REQUEST"

# TODO: This works only for a single alert rule. Update to handle multiple items.
## LOAD ALERTS RULE
PLATFORM_FOLDER=$(cat etc/grafana/init/folder-platform.json)
curl -v -s -X POST "${GRAFANA_URL}/api/folders" \
  -H "Content-Type: application/json" \
  -d "$PLATFORM_FOLDER"

ALERT_RULE=$(cat etc/grafana/init/alert-rule-request-per-second-rate-warn.json)
curl -v -s -X POST "${GRAFANA_URL}/api/v1/provisioning/alert-rules" \
  -H "X-Disable-Provenance: true" \
  -H "Content-Type: application/json" \
  -d "$ALERT_RULE"