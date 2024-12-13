# 학습지 API
### 프로젝트 개요
* 사용자가 선생님 혹은 학생인 학습지 서비스 API를 개발한다.
* 선생님이 학습지를 생성하고 학생에게 학습지를 출제하는 기능을 구현한다.

### 프로젝트 기술 구성요소
* Kotlin: 1.9.25
* Spring Boot: 2.7.18
* JPA: 1.9.24
* DBMS: H2
* Testing: JUnit, Mockito

### 프로젝트 구조
```
homeschoolmaterials
├─api
│  ├─advice
│  ├─error
│  ├─homeschool
│  │  ├─request
│  │  └─response
│  └─problem
│      ├─request
│      └─response
├─domain
│  ├─homeschool
│  │  └─dto
│  └─problem
│      └─dto
├─facade
└─infrastructure
    ├─homeschool
    │  └─entity
    └─problem
        └─entity
```


<img src="https://github.com/user-attachments/assets/d222f9cf-0e90-4d10-b36d-17843e658137" style="width: 300px">


### ERD
* 📇[ERD 문서](docs/ERD.md)

### 요구 API
#### 1️⃣ [GET] 문제 조회
##### API 스펙
* 🌐[[GET] /api/v1/problems API 구현](src/main/kotlin/org/freewheelin/homeschoolmaterials/api/problem/ProblemApi.kt)
* 📜[API Request 스펙](src/main/resources/http/problemApiRequest.http)

##### 관련 Issue
* ⚠️**Issue**: [API 1. 문제 조회 #1](https://github.com/lgm1007/home-school-materials-api/issues/1)
* 🔗**Pull Request**:
  * [[Feature] API 1. 문제 조회하기 작업](https://github.com/lgm1007/home-school-materials-api/pull/8)
  * [[Modify] API 1. 문제 조회 시 중복 순회 부분 한 번만 순회하도록 개선](https://github.com/lgm1007/home-school-materials-api/pull/9)

##### 설명
* 선생님은 총 문제 수, 유형코드 리스트, 문제 유형 (주관식, 객관식, 전체), 난이도를 조건으로 문제를 조회한다.
* **Request Param**
  * 총 문제 수 (totalCount)
    * 총 문제 수는 최대 문제 개수를 의미한다.
    * 조건들을 활용해서 조회한 문제의 수가 파라미터로 전달받은 총 문제 수보다 적다면 총 문제 수보다 적어도 괜찮다.
    * 조건들을 활용해서 조회한 문제의 수가 파라미터로 전달받은 총 문제 수보다 많다면 총 문제 수 만큼만 조회한다.
  * 유형 코드 리스트 (unitCodeList)
    * 파라미터로 전달받은 유형 코드 리스트 내에서만 문제를 조회한다.
  * 문제 유형 (problemType)
    * 주관식, 객관식, 전체 3개의 경우가 존재하며 전달받은 유형만 조회한다.
  * 난이도 (level)
    * 파라미터로 받을 수 있는 난이도는 상, 중, 하 3가지가 존재한다.
    * 하: 난이도 1
    * 중: 난이도 2, 3, 4
    * 상: 난이도 5
    * 난이도별 문제 비율은 아래와 같다. 전체 수는 파라미터로 받은 총 문제 수가 기준이다.
      * 하 선택 시: 하 문제 50%, 중 문제 30%, 상 문제 20%
      * 중 선택 시: 하 문제 20%, 중 문제 50%, 상 문제 30%
      * 상 선택 시: 하 문제 20%, 중 문제 30%, 상 문제 50%


#### 2️⃣ [POST] 학습지 생성
##### API 스펙
* 🌐[[POST] /api/v1/home-schools API 구현](src/main/kotlin/org/freewheelin/homeschoolmaterials/api/homeschool/HomeSchoolApi.kt)
* 📜[API Request 스펙](src/main/resources/http/homeSchoolApiRequest.http)

##### 관련 Issue
* ⚠️**Issue**: [API 2. 학습지 생성 #2](https://github.com/lgm1007/home-school-materials-api/issues/2)
* 🔗**Pull Request**:
  * [[Feature] API 2. 학습지 생성 기능 추가](https://github.com/lgm1007/home-school-materials-api/pull/11)
  * [[Modify]: API 2. 학습지 생성 시 학습지의 최대 문제 수 검사 수행](https://github.com/lgm1007/home-school-materials-api/pull/15)

##### 설명
* 선생님은 1번에서 조회한 문제 리스트를 바탕으로 학습지를 생성한다.
* 학습지 생성에 포함될 수 있는 최대 문제 개수는 50개이다.

#### 3️⃣ [POST] 학생에게 학습지 출제하기
##### API 스펙
* 🌐[[POST] /api/v1/home-schools/present API 구현](src/main/kotlin/org/freewheelin/homeschoolmaterials/api/homeschool/HomeSchoolApi.kt)
* 📜[API Request 스펙](src/main/resources/http/homeSchoolApiRequest.http)

##### 관련 Issue
* ⚠️**Issue**: [API 3. 학생에게 학습지 출제하기 #3](https://github.com/lgm1007/home-school-materials-api/issues/3)
* 🔗**Pull Request**:
  * [[Feature] API 3. 학생에게 학습지 출제 기능 추가](https://github.com/lgm1007/home-school-materials-api/pull/12)
  * [[Modify] API 3. 학생에게 학습지 출제 중 반복적인 출제 문제 데이터 저장 시 saveAll() 사용](https://github.com/lgm1007/home-school-materials-api/pull/14)
  * [[Modify]: API 3. 학습지 출제 시 이미 해당 학습지를 출제한 학생은 출제하지 않기 조건 추가](https://github.com/lgm1007/home-school-materials-api/pull/17)

##### 설명
* 선생님은 학생에게 2번에서 생성했던 학습지 1개를 출제한다.
* 학습지는 동시에 2명 이상의 학생에게 출제가 가능하다.
* 만약 학생 중 이미 같은 학습지를 출제받은 경우가 있으면 해당 학생은 제외하고 나머지 인원만 학습지를 출제받는다.

#### 4️⃣ [GET] 학습지의 문제 조회하기
##### API 스펙
* 🌐[[GET] /api/v1/problems/{homeSchoolId} API 구현](src/main/kotlin/org/freewheelin/homeschoolmaterials/api/problem/ProblemApi.kt)
* 📜[API Request 스펙](src/main/resources/http/problemApiRequest.http)

##### 관련 Issue
* ⚠️**Issue**: [API 4. 학습지의 문제 조회하기 #4](https://github.com/lgm1007/home-school-materials-api/issues/4)
* 🔗**Pull Request**:
  * [[Feature] API 4. 학습지의 문제 목록 조회 기능 추가](https://github.com/lgm1007/home-school-materials-api/pull/10)

##### 설명
* 학생은 자신에게 출제된 학습지의 문제 목록을 확인할 수 있다.
* 학습지 1개에 대한 문제 목록을 확인한다.

#### 5️⃣ [PUT] 채점하기
##### API 스펙
* 🌐[[PUT] /api/v1/problems/grade API 구현](src/main/kotlin/org/freewheelin/homeschoolmaterials/api/problem/ProblemApi.kt)
* 📜[API Request 스펙](src/main/resources/http/problemApiRequest.http)

##### 관련 Issue
* ⚠️**Issue**: [API 5. 채점하기 #5](https://github.com/lgm1007/home-school-materials-api/issues/5)
* 🔗**Pull Request**:
  * [[Feature] API 5. 채점하기 기능 추가](https://github.com/lgm1007/home-school-materials-api/pull/13)

##### 설명
* 학생은 4번에서 확인했던 문제들을 채점할 수 있다.
* 채점 결과는 맞음, 틀림 2가지 경우가 존재한다.

#### 6️⃣ [GET] 학습지 학습 통계 분석하기
##### API 스펙
* 🌐[[GET] /api/v1/home-schools/analyze/{homeSchoolId} API 구현](src/main/kotlin/org/freewheelin/homeschoolmaterials/api/homeschool/HomeSchoolApi.kt)
* 📜[API Request 스펙](src/main/resources/http/homeSchoolApiRequest.http)

##### 관련 Issue
* ⚠️**Issue**: [API 6. 학습지 학습 통계 분석하기 #6](https://github.com/lgm1007/home-school-materials-api/issues/6)
* 🔗**Pull Request**:
  * [[Feature] API 6. 학습지 학습 통계 분석 기능 구현](https://github.com/lgm1007/home-school-materials-api/pull/16)

##### 설명
* 선생님은 1개의 학습지에 대해 학생들의 학습 통계를 파악할 수 있다.
* 조회한 1개의 학습지에 대해 아래의 정보들을 파악할 수 있다.
  * 학습지 ID
  * 학습지 이름
  * 출제된 학생들의 목록
  * 학생 개별의 학습지 정답률
  * 학습지의 문제 별 정답률 (출제받은 학생들에 한해서)

### 발생 가능한 위험요소
#### API 3. 학생에게 학습지 출제하기 기능
* **다량의 INSERT 발생으로 인한 DB 성능 저하**
  * 학습지 출제하기 기능은 다음과 같은 프로세스로 진행된다.
    1. 출제된 학습지 데이터 (`GivenHomeSchool`)를 학생 별로 저장한다.
    2. 출제된 학습지와 매핑되는 출제 문제 상황 데이터 (`SubmittedProblem`)을 학습지의 문제 수만큼 저장한다.
    3. 예를 들어 학생 2명에게 문제가 10개인 학습지를 출제한다고 하면 출제된 학습지는 (학생 수 = 2)개 만큼 저장되고, 출제 문제 상황 데이터는 (학생 수 * 학습지의 문제 수 = 20)개 만큼 저장된다.
    4. 출제할 학생 수와 학습지의 문제 수 많아질수록 비례하여 INSERT 동작 수가 많아지며, 이는 DB 커넥션 자원 소모 및 메모리 고갈 문제로 이어진다.
  * 해결 방법 고민
    1. 최대 BULK_SIZE를 두고 정해진 개수만큼 INSERT 하기
       * 한 번에 INSERT 하는 개수가 지나치게 많아져 DB 커넥션 및 메모리 자원이 고갈될 수 있는 문제를 방지한다.
    2. 메시지 큐를 활용하여 대량 INSERT 요청을 큐에 나눠 점진적으로 INSERT 하기
       * 큐에 나눠 처리하면 메모리나 DB에 대한 부담을 줄일 수 있다.
       * 메시지 큐를 활용해 비동기 방식으로 처리하면 동시에 다른 학습지 출제 요청이 발생해도 안정적이게 처리 가능하다.

#### API 6. 학습지 학습 통계 분석하기 기능
* **여러 조회 쿼리 발생 및 복잡한 통계 데이터 생성 로직**
  * 학습 통계 데이터를 생성하기 위해 출제된 학습지와 출제 문제 상황 등 여러 테이블에서 필요한 데이터를 조회, 학생 별/문제 별 정답률 계산 등 필요한 데이터를 가공하는 작업을 한다.
  * 해결 방법 고민
    1. 캐시 활용하기
       * 캐시를 활용하면 통계 데이터를 만들기 위한 조회 쿼리와 복잡한 로직 수행 빈도 수를 감소하게 되어 성능이 향상된다.
    2. 미리 계산된 통계 데이터 저장
       * Spring Batch나 Airflow 같은 배치 작업 툴을 사용하여 통계 데이터를 미리 계산하여 결과를 별도의 테이블에 저장하는 방식
       * 실시간 계산에 대한 부담을 줄이고 통계 데이터를 조회하는 속도를 개선하는 효과를 얻을 수 있다.
