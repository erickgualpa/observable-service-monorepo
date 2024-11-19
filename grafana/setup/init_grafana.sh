#!/bin/bash
GRAFANA_URL="http://$GRAFANA_USER:$GRAFANA_PASSWORD@grafana:3000"

## LOAD DASHBOARD
DASHBOARD=$(cat etc/grafana/init/dashboard.json)
CREATE_DASHBOARD_REQUEST="{
  \"dashboard\":$DASHBOARD
}"

curl -v -s -X POST "${GRAFANA_URL}/api/dashboards/db" \
  -H "Content-Type: application/json" \
  -d "$CREATE_DASHBOARD_REQUEST"

## LOAD 'Platform' folder
PLATFORM_FOLDER=$(cat etc/grafana/init/folder-platform.json)
curl -v -s -X POST "${GRAFANA_URL}/api/folders" \
  -H "Content-Type: application/json" \
  -d "$PLATFORM_FOLDER"

## LOAD ALERT RULES
ALERT_RULES_DIR="etc/grafana/init/alert-rules/"
for ALERT_RULE_FILE in "$ALERT_RULES_DIR"*.json; do
  ALERT_RULE=$(cat "$ALERT_RULE_FILE")
  curl -v -s -X POST "${GRAFANA_URL}/api/v1/provisioning/alert-rules" \
    -H "X-Disable-Provenance: true" \
    -H "Content-Type: application/json" \
    -d "$ALERT_RULE"
done