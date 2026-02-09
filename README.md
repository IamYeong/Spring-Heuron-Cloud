# [ HEURON ] B/E Coding Test

## Architecture

domain 패키지를 중심으로 api 패키지 혹은 web 패키지로 확장하는 구조입니다.
domain 패키지는 비즈니스 로직에 집중할 수 있는 장점이 있습니다.

### domain
- 도메인 계층별로 정리합니다(patient ...)
- entity : JPA 엔티티나 영속성 객체가 포함됩니다.
- repository : JPA 레포지토리 혹은 api / web 패키지에서 구현할 레포지토리 인터페이스를 정의합니다.
- service : 도메인에 관련된 코어 로직을 구현합니다.

### api
- 도메인 계층별로 동일하게 정리합니다.
- dto : 요청 DTO, 응답 DTO 를 정의합니다.
- service : domain 서비스를 이용하여 응용 기능을 정의합니다. 사용자 요구사항에 따라 유연하게 변경합니다.
- controller : api service 에만 의존하여 실제 API 를 구현합니다.

### web
- Thymeleaf 를 구현하여 백오피스나 간단한 웹을 띄울 경우 api 패키지와 동일한 요령으로 구현합니다.

### global
- 전역 설정 등에 활용합니다.

---

## Features

swagger-ui : /swagger-ui/index.html

### 환자 기본정보 저장 API
- Request : 
    - body : 이름 / 성별 / 생년월일 / 질병여부 업로드
    - 생년월일 : 병원마다 나이 기준이 달랐기 때문에 생년월일 관리가 유리했습니다
- Response : 웹/앱 에서 이미지 저장에 활용가능한 ID 응답

### 이미지 저장 API
- Request :
    - path variable : Patient ID
    - request parameter : jpeg 파일(Multipart)

### 환자 목록 조회 API
- Response : 
    - 이름 / 성별 / 나이 / 질병여부 / 이미지 url 목록
    - 병원에서는 데이터를 한 눈에 확인가능한 목록을 선호하는 일이 많았기에 목록으로 구현했습니다.
    - 한명씩 조회하는 기능이 필요하다면 domain service 의 getPatient() 를 사용해서 빠른 구현이 가능합니다.
    
### 환자 삭제 API
- Request :
    - pat variable : Patient ID