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

### 요구 API
#### 1️⃣ [GET] 문제 조회
##### API 스펙
* [MOCK API 구현](src/main/kotlin/org/freewheelin/homeschoolmaterials/api/problem/ProblemApi.kt)
* [API Request 스펙](src/main/resources/http/problemApiRequest.http)

##### 관련 Issue
* **Issue**: [API 1. 문제 조회 #1](https://github.com/lgm1007/home-school-materials-api/issues/1)
* **Pull Request**:
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
      * 중 선택 시: 하 문제 25%, 중 문제 50%, 상 문제 25%
      * 상 선택 시: 하 문제 20%, 중 문제 30%, 상 문제 50%


#### 2️⃣ [POST] 학습지 생성
##### API 스펙
* [MOCK API 구현](src/main/kotlin/org/freewheelin/homeschoolmaterials/api/homeschool/HomeSchoolApi.kt)
* [API Request 스펙](src/main/resources/http/homeSchoolApiRequest.http)

##### 관련 Issue
* **Issue**: [API 2. 학습지 생성 #2](https://github.com/lgm1007/home-school-materials-api/issues/2)
* **Pull Request**:
  * [[Feature] API 2. 학습지 생성 기능 추가](https://github.com/lgm1007/home-school-materials-api/pull/11)
  * [[Modify]: API 2. 학습지 생성 시 학습지의 최대 문제 수 검사 수행](https://github.com/lgm1007/home-school-materials-api/pull/15)

##### 설명
* 선생님은 1번에서 조회한 문제 리스트를 바탕으로 학습지를 생성한다.
* 학습지 생성에 포함될 수 있는 최대 문제 개수는 50개이다.

#### 3️⃣ [POST] 학생에게 학습지 출제하기
##### API 스펙
* [MOCK API 구현](src/main/kotlin/org/freewheelin/homeschoolmaterials/api/homeschool/HomeSchoolApi.kt)
* [API Request 스펙](src/main/resources/http/homeSchoolApiRequest.http)

##### 관련 Issue
* **Issue**: [API 3. 학생에게 학습지 출제하기 #3](https://github.com/lgm1007/home-school-materials-api/issues/3)
* **Pull Request**:
  * [[Feature] API 3. 학생에게 학습지 출제 기능 추가](https://github.com/lgm1007/home-school-materials-api/pull/12)
  * [[Modify] API 3. 학생에게 학습지 출제 중 반복적인 출제 문제 데이터 저장 시 saveAll() 사용](https://github.com/lgm1007/home-school-materials-api/pull/14)

##### 설명
* 선생님은 학생에게 2번에서 생성했던 학습지 1개를 출제한다.
* 학습지는 동시에 2명 이상의 학생에게 출제가 가능하다.
* 만약 학생 중 이미 같은 학습지를 출제받은 경우가 있으면 해당 학생은 제외하고 나머지 인원만 학습지를 출제받는다.

#### 4️⃣ [GET] 학습지의 문제 조회하기
##### API 스펙
* [MOCK API 구현](src/main/kotlin/org/freewheelin/homeschoolmaterials/api/problem/ProblemApi.kt)
* [API Request 스펙](src/main/resources/http/problemApiRequest.http)

##### 관련 Issue
* **Issue**: [API 4. 학습지의 문제 조회하기 #4](https://github.com/lgm1007/home-school-materials-api/issues/4)
* **Pull Request**:
  * [[Feature] API 4. 학습지의 문제 목록 조회 기능 추가](https://github.com/lgm1007/home-school-materials-api/pull/10)

##### 설명
* 학생은 자신에게 출제된 학습지의 문제 목록을 확인할 수 있다.
* 학습지 1개에 대한 문제 목록을 확인한다.

#### 5️⃣ [PUT] 채점하기
##### API 스펙
* [MOCK API 구현](src/main/kotlin/org/freewheelin/homeschoolmaterials/api/problem/ProblemApi.kt)
* [API Request 스펙](src/main/resources/http/problemApiRequest.http)

##### 관련 Issue
* **Issue**: [API 5. 채점하기 #5](https://github.com/lgm1007/home-school-materials-api/issues/5)
* **Pull Request**:
  * [[Feature] API 5. 채점하기 기능 추가](https://github.com/lgm1007/home-school-materials-api/pull/13)

##### 설명
* 학생은 4번에서 확인했던 문제들을 채점할 수 있다.
* 채점 결과는 맞음, 틀림 2가지 경우가 존재한다.

#### 6️⃣ [GET] 학습지 학습 통계 분석하기
##### API 스펙
* [MOCK API 구현](src/main/kotlin/org/freewheelin/homeschoolmaterials/api/homeschool/HomeSchoolApi.kt)
* [API Request 스펙](src/main/resources/http/homeSchoolApiRequest.http)

##### 관련 Issue
* **Issue**: [API 6. 학습지 학습 통계 분석하기 #6](https://github.com/lgm1007/home-school-materials-api/issues/6)
* **Pull Request**:
  * [[Feature] API 6. 학습지 학습 통계 분석 기능 구현](https://github.com/lgm1007/home-school-materials-api/pull/16)

##### 설명
* 선생님은 1개의 학습지에 대해 학생들의 학습 통계를 파악할 수 있다.
* 조회한 1개의 학습지에 대해 아래의 정보들을 파악할 수 있다.
  * 학습지 ID
  * 학습지 이름
  * 출제된 학생들의 목록
  * 학생 개별의 학습지 정답률
  * 학습지의 문제 별 정답률 (출제받은 학생들에 한해서)

### ERD
[ERD 문서](docs/ERD.md)
