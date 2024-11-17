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

## LOAD 'Platform' folder
PLATFORM_FOLDER=$(cat etc/grafana/init/folder-platform.json)
curl -v -s -X POST "${GRAFANA_URL}/api/folders" \
  -H "Content-Type: application/json" \
  -d "$PLATFORM_FOLDER"

# TODO: This works only for a single alert rule. Update to handle multiple items.
## LOAD ALERTS RULE
ALERT_RULE_REQUEST_PER_SECOND_RATE_WARN=$(cat etc/grafana/init/alert-rule-request-per-second-rate-warn.json)
curl -v -s -X POST "${GRAFANA_URL}/api/v1/provisioning/alert-rules" \
  -H "X-Disable-Provenance: true" \
  -H "Content-Type: application/json" \
  -d "$ALERT_RULE_REQUEST_PER_SECOND_RATE_WARN"

ALERT_RULE_ERROR_OCCURRED=$(cat etc/grafana/init/alert-rule-error-occurred.json)
curl -v -s -X POST "${GRAFANA_URL}/api/v1/provisioning/alert-rules" \
  -H "X-Disable-Provenance: true" \
  -H "Content-Type: application/json" \
  -d "$ALERT_RULE_ERROR_OCCURRED"
