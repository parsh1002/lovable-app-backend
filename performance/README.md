# Lovable API Performance Testing

## Overview

I used Apache JMeter to evaluate the performance of the Lovable Spring Boot backend under different levels of concurrent load. The goal was to measure response latency, throughput, and the observed error rate for the matches endpoint.

## Endpoint Tested

- **Method:** GET
- **Endpoint:** `/api/matches`
- **Purpose:** Retrieve matches associated with the authenticated user.
- **Tool:** Apache JMeter

## Benchmark Results

| Metric | 100 Virtual Users | 1,000 Virtual Users |
|---|---:|---:|
| Requests | 200,000 | 200,000 |
| Average latency | 39 ms | 410 ms |
| P95 latency | 61 ms | 472 ms |
| P99 latency | 72 ms | 508 ms |
| Maximum latency | 146 ms | 643 ms |
| Observed error rate | 0% | 0% |

## Key Observations

The API maintained a 0% observed error rate in both recorded tests. Increasing the configured virtual-user count was associated with higher response latency, particularly at the 1,000-user level.

These results represent local load tests and should not be interpreted as production capacity guarantees.

## Reproducing the Test

1. Start the Lovable backend and PostgreSQL.
2. Configure a valid JWT locally.
3. Open the JMeter test plan.
4. Set the desired Thread Group configuration.
5. Clear previous results and run the test.
6. Review the Aggregate Report for average latency, P95, P99, throughput, and error rate.

## Evidence

See the screenshots in the `screenshots/` directory for the recorded JMeter Aggregate Reports.
