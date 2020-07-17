# Chinstagram API 설계

'친해지길바래' 팀에서 작성한 Chinstagram API 설계서입니다. (feat. 고정민, 심수연)

### Base URL

http://localhost:port/chinstagram

### 기능별 설계

#### 1. 회원 관리

- Endpoint
  - /user

##### 1.1 회원 가입

- Endpoint

  - /user/signup

- HTTP Request Method

  - POST

- Parameters

  - path

    - 없음

  - query string

    - 없음

  - request header

    - 없음

  - request body

    | Parameter | Description                                    |
    | --------- | ---------------------------------------------- |
    | id        | This is user id for log-in.                    |
    | email     | This is user email address for authentication. |
    | name      | This is real user name.                        |
    | pw        | This is encrypted user password for log-in.    |

- HTTP Request Body

  ````json
  {
    "id": "string",
    "email": "user@email.com",
    "name": "string",
    "pw": "encrypted string"
  }
  ````

- HTTP Response Body

  - Status code: 2xx

  ```json
  {
    "id": "string",
    "email": "user@email.com",
    "name": "string"
  }
  ```

  - Status code: 400

  ```json
  {
    "error": "Bad Request",
    "message": "Invalid request parameters."
  }
  ```

  - Status code: 401

  ```json
  {
    "error": "Unauthorized",
    "message": "Authorization token has been refused."
  }
  ```

  - Status code: 403

  ```json
  {
    "error": "Forbidden",
    "message": "Permission denied."
  }
  ```

  - Status code: 404

  ```json
  {
    "error": "Not Found",
    "message": "Invalid URI."
  }
  ```

  - Status code: 500

  ```json
  {
    "error": "Internal Server Error",
    "message": "No message available."
  }
  ```

##### 1.2 로그인

- Endpoint

  - /user/login

- HTTP Request Method

  - POST

- Parameters

  - path
    
    - 없음
    
  - query string
    
    - 없음
    
  - request header

    - 없음

  - request body

    | Parameter | Description                                 |
    | --------- | ------------------------------------------- |
    | id        | This is user id for log-in.                 |
    | pw        | This is encrypted user password for log-in. |

- HTTP Request Body

  ```json
  {
    "id": "string",
    "pw": "encrypted string"
  }
  ```

- HTTP Response Body

  - Status code: 2xx

  ```json
  {
    "access-token": "dGVzdHVzZXIwMTpuZXRjb29s"
  }
  ```

  - Status code: 400

  ```json
  {
    "error": "Bad request",
    "message": "Invalid request parameters."
  }
  ```

  - Status code: 401

  ```json
  {
    "error": "Unauthorized",
    "message": "Authorization token has been refused."
  }
  ```

  - Status code: 403

  ```json
  {
    "error": "Forbidden",
    "message": "Permission denied."
  }
  ```

  - Status code: 404

  ```json
  {
    "error": "Not Found",
    "message": "Invalid URI."
  }
  ```

  - Status code: 500

  ```json
  {
    "error": "Internal Server Error",
    "message": "No message available."
  }
  ```


##### 1.3 프로필 수정

- Endpoint

  - /user/profile/{id}

- HTTP Request Method

  - PUT

- Parameters

  - path

    | Parameter | Description                 |
    | --------- | --------------------------- |
    | id        | This is user id for log-in. |

  - query string

    - 없음
    
  - request header

    | Parameter     | Description                                                 |
    | ------------- | ----------------------------------------------------------- |
    | authorization | This is authorization token to access account after log-in. |

  - request body

    | Parameter | Description                                                  |
    | --------- | ------------------------------------------------------------ |
    | nickname  | This is nickname set by user.                                |
    | intro     | This is introduction text written by user.                   |
    | image     | This is profile image set by user. Contains name, path information. |

- HTTP Request Body

  ```json
  {
    "nickname": "string",
    "intro": "string",
    "image": {
      "name": "string",
      "path": "string"
    }
  }
  ```

- HTTP Response Body

  - Status code: 2xx

  ```json
  {
    "nickname": "string",
    "intro": "string",
    "image": {
      "name": "string",
      "path": "string"
    },
    "createTime": "2020-07-15T15:31:53.029Z",
    "updateTime": "2020-07-15T16:29:57.281Z"
  }
  ```

  - Status code: 400

  ```json
  {
    "error": "Bad Request",
    "message": "Invalid request parameters."
  }
  ```

  - Status code: 401

  ```json
  {
    "error": "Unauthorized",
    "message": "Authorization token has been refused."
  }
  ```

  - Status code: 403

  ```json
  {
    "error": "Forbidden",
    "message": "Permission denied."
  }
  ```

  - Status code: 404

  ```json
  {
    "error": "Not Found",
    "message": "Invalid URI."
  }
  ```

  - Status code: 500

  ```json
  {
    "error": "Internal Server Error",
    "message": "No message available."
  }
  ```

#### 2. 게시글 관리

- Endpoint
  - /content

##### 2.1 게시글 불러오기

- Endpoint

  - /content

- HTTP Request Method

  - GET

- Parameters

  - path

    - 없음

  - query string

    | Parameter | Description                                              |
    | --------- | -------------------------------------------------------- |
    | skip      | This is skip for paging start index.                     |
    | limit     | This is limit for how many contents will be in one page. |

  - request header

    | Parameter     | Description                                                 |
    | ------------- | ----------------------------------------------------------- |
    | authorization | This is authorization token to access account after log-in. |

  - request body

    - 없음

- HTTP Request Body

  - 없음
  
- HTTP Response Body

  - Status code: 2xx

  ```json
  {
    "contents": [{
      "id": int,
      "script": "string",	// 게시글의 내용
      "images": [{	// 대표 이미지는 첫번째 사진으로 설정
        "name": "string",
        "path": "string"
      }],
      "likes": int,
      "comment": "string",
      "createTime": "2020-07-15T15:31:53.029Z"	// 게시글이 생성된 시간
    }]
  }
  ```

  - Status code: 400

  ```json
  {
    "error": "Bad Request",
    "message": "Invalid request parameters."
  }
  ```

  - Status code: 401

  ```json
  {
    "error": "Unauthorized",
    "message": "Authorization token has been refused."
  }
  ```

  - Status code: 403

  ```json
  {
    "error": "Forbidden",
    "message": "Permission denied."
  }
  ```

  - Status code: 404

  ```json
  {
    "error": "Not Found",
    "message": "Invalid URI."
  }
  ```

  - Status code: 500

  ```json
  {
    "error": "Internal Server Error",
    "message": "No message available."
  }
  ```


##### 2.2 게시글 수정

- Endpoint

  - /content/{id}

- HTTP Request Method

  - PUT

- Parameters

  - path

    | Parameter | Description                   |
    | --------- | ----------------------------- |
    | id        | This is id given per content. |

  - query string

    - 없음

  - request header

    | Parameter     | Description                                                 |
    | ------------- | ----------------------------------------------------------- |
    | authorization | This is authorization token to access account after log-in. |

  - request body

    | Parameter | Description                                                  |
    | --------- | ------------------------------------------------------------ |
    | id        | This is id given per content.                                |
    | script    | This is script written per content by user.                  |
    | images    | This is content images uploaded by user. Contain name, path of each image. |

- HTTP Request Body

  ```json
  {
    "id": int,
    "script": "string",
    "images": [{
      "name": "string",
      "path": "string"
    }]
  }
  ```
  
- HTTP Response Body

  - Status code: 2xx

  ```json
  {
    "id": int,
    "script": "string",
    "images": [{
      "name": "string",
      "path": "string"
    }],
    "createTime": "2020-07-15T15:31:53.029Z",
    "updateTime": "2020-07-15T16:29:57.281Z"
  }
  ```

  - Status code: 400

  ```json
  {
    "error": "Bad Request",
    "message": "Invalid request parameters."
  }
  ```

  - Status code: 401

  ```json
  {
    "error": "Unauthorized",
    "message": "Authorization token has been refused."
  }
  ```

  - Status code: 403

  ```json
  {
    "error": "Forbidden",
    "message": "Permission denied."
  }
  ```

  - Status code: 404

  ```json
  {
    "error": "Not Found",
    "message": "Invalid URI."
  }
  ```

  - Status code: 500

  ```json
  {
    "error": "Internal Server Error"
    "message": "No message available."
  }
  ```


##### 2.3 게시글 삭제

- Endpoint

  - /content/{id}

- HTTP Request Method

  - DELETE

- Parameters

  - path

    | Parameter | Description                   |
    | --------- | ----------------------------- |
    | id        | This is id given per content. |

  - query string

    - 없음

  - request header

    | Parameter     | Description                                                 |
    | ------------- | ----------------------------------------------------------- |
    | authorization | This is authorization token to access account after log-in. |

  - request body

    - 없음

- HTTP Request Body

  - 없음
  
- HTTP Response Body

  - Status code: 204

  ```
  No Content
  ```

  - Status code: 400

  ```json
  {
    "error": "Bad Request",
    "message": "Invalid request parameters."
  }
  ```

  - Status code: 401

  ```json
  {
    "error": "Unauthorized",
    "message": "Authorization token has been refused."
  }
  ```

  - Status code: 403

  ```json
  {
    "error": "Forbidden",
    "message": "Permission denied."
  }
  ```

  - Status code: 404

  ```json
  {
    "error": "Not Found",
    "message": "Invalid URI."
  }
  ```

  - Status code: 500

  ```json
  {
    "error": "Internal Server Error",
    "message": "No message available."
  }
  ```

##### 2.4 게시글 업로드

- Endpoint

  - /content

- HTTP Request Method

  - POST

- Parameters

  - path

    - 없음

  - query string

    - 없음

  - request header

    | Parameter     | Description                                                 |
    | ------------- | ----------------------------------------------------------- |
    | authorization | This is authorization token to access account after log-in. |

  - request body

    | Parameter | Description                                                  |
    | --------- | ------------------------------------------------------------ |
    | id        | This is id given per content.                                |
    | script    | This is script written per content by user.                  |
    | images    | This is content images uploaded by user. Contain name, path of each image. |

- HTTP Request Body

  ```json
  {
    "id": int,
    "script": "string",
    "images": [{
      "name": "string",
      "path": "string"
    }]
  }
  ```
  
- HTTP Response Body

  - Status code: 2xx

  ```json
  {
    "id": int,
    "script": "string",
    "images": [{	// 이미지 파일 S3에 업로드는 서버에서 처리
      "name": "string",
      "path": "string"
    }],
    "createTime": "2020-07-15T15:31:53.029Z"	// 게시글이 생성된 시간
  }
  ```

  - Status code: 400

  ```json
  {
    "error": "Bad Request",
    "message": "Invalid request parameters."
  }
  ```

  - Status code: 401

  ```json
  {
    "error": "Unauthorized",
    "message": "Authorization token has been refused."
  }
  ```

  - Status code: 403

  ```json
  {
    "error": "Forbidden",
    "message": "Permission denied."
  }
  ```

  - Status code: 404

  ```json
  {
    "error": "Not Found",
    "message": "Invalid URI."
  }
  ```

  - Status code: 500

  ```json
  {
    "error": "Internal Server Error",
    "message": "No message available."
  }
  ```



