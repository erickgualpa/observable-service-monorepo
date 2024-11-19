#!/bin/bash
function createWallet() {
    local WALLET_ID=$(uuidgen)

    local OWNER_ID=$(uuidgen)
    local OWNER_USERNAME=$(openssl rand -base64 6 | tr -dc 'a-zA-Z0-9' | head -c8)

    local ACCOUNT_ID=$(uuidgen)
    local ACCOUNT_CURRENCY="EUR"

    local PUT_WALLET_REQUEST="\
    { \
      \"wallet\": { \
        \"id\": \"$WALLET_ID\", \
        \"owner\": { \
          \"id\": \"$OWNER_ID\", \
          \"username\": \"$OWNER_USERNAME\" \
        }, \
        \"account\": { \
          \"id\": \"$ACCOUNT_ID\", \
          \"currency\": \"$ACCOUNT_CURRENCY\" \
        } \
      } \
    }"

    curl -X PUT \
      "http://localhost:8080/v1/wallets" \
      -H "accept: */*" \
      -H "Content-Type: application/json" \
      -d "$PUT_WALLET_REQUEST"

    for (( j = 0; j < 20; j++ )); do
        requestDeposit $WALLET_ID
    done
}

function requestDeposit() {
    local WALLET_ID=$1

    local DEPOSIT_ID=$(uuidgen)
    local DEPOSIT_AMOUNT=$(awk -v min=5 -v max=300 'BEGIN{srand(); print min+rand()*(max-min)}')
    local DEPOSIT_CURRENCY="EUR"

    local PUT_DEPOSIT_REQUEST="\
    { \
      \"deposit\": { \
        \"id\": \"$DEPOSIT_ID\", \
        \"amount\": \"$DEPOSIT_AMOUNT\", \
        \"currency\": \"$DEPOSIT_CURRENCY\" \
      } \
    }"

    curl -X PUT \
      "http://localhost:8080/v2/wallets/$WALLET_ID/deposit" \
      -H "accept: */*" \
      -H "Content-Type: application/json" \
      -d "$PUT_DEPOSIT_REQUEST"
}

for (( i = 0; i < 100000000; i++ )); do
    createWallet
done