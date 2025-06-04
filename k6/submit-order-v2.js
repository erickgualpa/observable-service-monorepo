import http from 'k6/http';
import { check, sleep } from 'k6';
import { uuidv4 } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

export const options = {
  stages: [
    { duration: '5s', target: 500 },   // Ramp up
    { duration: '15s', target: 5000 },  // Stay at 5000 users
    { duration: '5s', target: 500 },   // Ramp down
  ],
};

const orderTypes = ['standard', 'express', 'priority'];
const customers = ['customer_1', 'customer_2', 'customer_3'];

export default function () {
  const url = 'http://localhost:8080/v1/orders';

  const payload = JSON.stringify({
    order: {
      id: uuidv4(),
      type: orderTypes[Math.floor(Math.random() * orderTypes.length)],
      customerId: customers[Math.floor(Math.random() * customers.length)],
      timestamp: new Date().toISOString(),
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

  // Variable sleep to simulate realistic user behavior
  sleep(Math.random() * 2 + 0.5); // 0.5 to 2.5 seconds
}