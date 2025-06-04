import http from 'k6/http';
import { check, sleep } from 'k6';
import { uuidv4 } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

export const options = {
  vus: 100,
  duration: '10s',
};

export default function () {
  const url = 'http://localhost:8080/v1/orders';

  const payload = JSON.stringify({
    order: {
      id: uuidv4(),
    },
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const res = http.put(url, payload, params);

  check(res, {
    'status is 204': (r) => r.status === 204
  });

  sleep(1);
}