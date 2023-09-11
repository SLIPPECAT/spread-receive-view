# spread-receive-view

### 작업 환경
#### springboot: 2.7.10, java 11, maridaDB

### 요구사항
- [x] 작업 항목 1 뿌리기 받기 조회 기능을 수행하는 REST API 구현
- [x] 작업 항목 2 요청한 사용자의 식별값은 숫자 형태이며 "X-USER-ID" 라는 HTTP Header로 전달됩니다. 적용
- [x] 작업 항목 3 요청한 사용자가 속한 대화방의 식별값은 문자 형태이며 "X-ROOM-ID" 라는 HTTP Header로 전달됩니다. 적용
- [x] 모든 사용자는 뿌리기에 충분한 잔액을 보유하고 있다고 가정하여 별도로 잔액에 관련된 체크는 하지 않습니다. 적용
- [ ] 작성하신 어플리케이션이 다수의 서버에 다수의 인스턴스로 동작하더라도 기능에 문제가 없도록 설계
- [ ] 각 기능 및 제약사항에 대한 단위테스트를 반드시 작성합니다.

### 아키텍쳐
![스크린샷 2023-09-12 오전 12 01 59](https://github.com/SLIPPECAT/spread-receive-view/assets/119715555/c06dcb88-c46c-4ed1-a664-3fdd30d496ce)



### API
- 뿌리기 API: @Header: X-USER-ID, X-ROOM-ID, amount, recipients 입력받고 token을 반환 받음.
- 받기 API: @Header: X-USER-ID, X-ROOM-ID, token을 입력받고 뿌리기로 받은 금액 amount를 반환 받음.
- 조회 API: @Header: X-USER-ID, token을 입력 받고, spread의 데이터(날짜, 총 뿌린 금액, 할당된 금액, 유저별 할당 상세 정보)를 반환

### 예외처리
#### 뿌리기 API

| 예외 코드 | 설명 | 상태 코드 |
|---|---|---|
| INVALID_RECIPIENT | 뿌리기 대상이 없는 경우(0인 경우). | 400 |

#### 받기 API
| 예외 코드 | 설명 | 상태 코드 |
|---|---|---|
| INVALID_TOKEN | 유효하지 않은 토큰일 경우 | 401 |
| MISMATCHED_ROOM | 뿌린 대화방과 다른 대화방일 경우 | 401 |
| INVALID_TIME | 뿌리기 이후 10분이 넘어서 받기를 실행한 경우 | 401 |
| NOT_RECIPIENT | 뿌리기를 한 user가 본인이 받는 경우 | 401 |
| ALREADY_RECEIVE | 한 번 뿌리기를 받은 user가 다시 받는 경우 | 401 |

#### 조회 API
| 예외 코드 | 설명 | 상태 코드 |
|---|---|---|
| INVALID_TOKEN | 토큰값이 올바르지 않은 경우 | 402 |
| INVALID_DATE | 7일이 지나 조회를 한 경우 | 402 |
| NOT_ACCESSED | 뿌리기 건에 대해 받기를 한 유저가 아닌 경우 | 402 |
| NOT_FOUND_SPREAD | 뿌리기 건에 대해 받기를 한 유저가 아닌 경우 | 402 |

#### 요청 사항 불충분
- 다수의 서버에 다수의 인스턴스로 동작하는 지와 관련하여 테스트를 진행한 후 비동기 실행 및 캐싱과 관련한 작업으로 개선할 수 있을 것으로 생각됩니다.
하지만 실행을 하지는 못했습니다..
- 단위 테스트를 작성해서 기능에 대한 충분한 확인을 해야 했지만 그러하지 못했습니다.
