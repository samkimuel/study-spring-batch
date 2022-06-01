# 스프링 배치 냄새 맡기

- [조졸두님의 Spring Batch 가이드 1](https://jojoldu.tistory.com/324)
- [조졸두님의 Spring Batch 가이드 2](https://jojoldu.tistory.com/325)
- [조졸두님의 Spring Batch 가이드 3](https://jojoldu.tistory.com/326)
- [조졸두님의 Spring Batch 가이드 4](https://jojoldu.tistory.com/328)

## 기록

#### BatchStatus와 ExitStatus의 차이
- BatchStatus는 Job 또는 Step의 실행 결과를 Spring에서 기록할 때 사용 - Enum
- ExitStatus는 Step의 실행 후 상태 - Enum 아님
- Spring Batch는 기본적으로 ExitStatus의 exitCode는 Step의 BatchStatus와 같도록 설정되어 있음
- 커스텀한 exitCode 구현 가능
