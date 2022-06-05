# 스프링 배치 냄새 맡기

- [조졸두님의 Spring Batch 가이드 1](https://jojoldu.tistory.com/324)
- [조졸두님의 Spring Batch 가이드 2](https://jojoldu.tistory.com/325)
- [조졸두님의 Spring Batch 가이드 3](https://jojoldu.tistory.com/326)
- [조졸두님의 Spring Batch 가이드 4](https://jojoldu.tistory.com/328)
- [조졸두님의 Spring Batch 가이드 5](https://jojoldu.tistory.com/330)

## 기록

#### BatchStatus와 ExitStatus의 차이
- BatchStatus는 Job 또는 Step의 실행 결과를 Spring에서 기록할 때 사용 - Enum
- ExitStatus는 Step의 실행 후 상태 - Enum 아님
- Spring Batch는 기본적으로 ExitStatus의 exitCode는 Step의 BatchStatus와 같도록 설정되어 있음
- 커스텀한 exitCode 구현 가능

#### JobParameters
- @JobScope는 Step, @StepScope는 Tasklet이나 ItemReader, ItemWriter, ItemProcessor에서 사용
- @JobScope, @StepScope -> Job, Step 실행 시점에 해당 컴포넌트를 Spring Bean으로 생성 - Bean의 생성 시점을 지정된 Scope가 실행되는 시점으로 지연시킴
- 장점
  - JobParameter의 Late Binding이 가능함
  - 동일한 컴포넌트를 병렬 혹은 동시에 사용할 때 유용함
