# 2021-07-14

- Try to use `echarts-for-react`:
  - `echarts-for-reacts` depends on `tslib`, which introduces 1M+ extra distribution size (1.7M->2.7M)
  - It's difficult to construct a complex native JS object in kotlin
