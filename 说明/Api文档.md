# Guagua_sys

# Authentication

# AuthController

## POST login

POST /api/auth/login

> Body 请求参数

```json
{
  "username": "string",
  "password": "string",
  "role": "string"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|Authorization|header|string| 否 ||token|
|body|body|[UserDTO](#schemauserdto)| 否 |  |登录用户信息|

> 返回示例

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "userInfo": {
      "id": 1,
      "role": "STUDENT",
      "username": "student",
      "name": "student",
      "no": null
    },
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiU1RVREVOVCIsInN1YiI6InN0dWRlbnQiLCJpYXQiOjE3NDg0ODgwNTUsImV4cCI6MTc0ODQ5MTY1NX0.KQ7oWnIfZ5HYuMsbvxtG0bDsdr3CCStaajou3ND6mvH_JJ6bZ7hBd6oxIUPwpODyZ1j9OPfS02c6cvOJgZ_Uhg"
  },
  "timestamp": 1748488055439
}
```

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "userInfo": {
      "id": 2,
      "role": "TEACHER",
      "username": "teacher",
      "name": "teacher",
      "no": null
    },
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiVEVBQ0hFUiIsInN1YiI6InRlYWNoZXIiLCJpYXQiOjE3NDg0ODgxMjAsImV4cCI6MTc0ODQ5MTcyMH0.7xWXiHyJr40-ppeYIfDKqAew-c1OwDHjyD4OhR18WOl7RkqJq8H6JSG2tNpeTdhYmBAQILZ7gf07BAF0TBLDNg"
  },
  "timestamp": 1748488120071
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|[ResultMapObject](#schemaresultmapobject)|

## POST register

POST /api/auth/register

> Body 请求参数

```json
{
  "username": "string",
  "password": "string",
  "role": "string"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|Authorization|header|string| 否 ||none|
|body|body|[UserDTO](#schemauserdto)| 否 | 登录用户信息|none|

> 返回示例

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "username": "student",
    "password": "123456",
    "role": "STUDENT",
    "status": 0,
    "createTime": "2025-05-29T11:01:45.9805528",
    "updateTime": "2025-05-29T11:01:45.9805528"
  },
  "timestamp": 1748487706044
}
```

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 2,
    "username": "teacher",
    "password": "123456",
    "role": "TEACHER",
    "status": 0,
    "createTime": "2025-05-29T11:02:35.219388",
    "updateTime": "2025-05-29T11:02:35.219388"
  },
  "timestamp": 1748487755252
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|[ResultUser](#schemaresultuser)|

## PUT logout

PUT /api/auth/{username}/logout

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|username|path|string| 是 ||none|
|Authorization|header|string| 否 ||none|

> 返回示例

> 200 Response

```json
{
  "code": 200,
  "message": "操作成功",
  "data": null,
  "timestamp": 1748488843243
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|[ResultUser](#schemaresultuser)|

# StudentController

## GET getInfo

GET /api/student/{username}

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|username|path|string| 是 ||用户名|
|Authorization|header|string| 否 ||none|

> 返回示例

> 200 Response

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "username": "student",
    "password": "123456",
    "name": "student",
    "studentNo": null
  },
  "timestamp": 1748488238463
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|[ResultStudentDTO](#schemaresultstudentdto)|

## PUT changeInfo

PUT /api/student/{username}

改变信息后会自动退出登录 

> Body 请求参数

```json
{
  "username": "string",
  "password": "string",
  "name": "string",
  "studentNo": "string"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|username|path|string| 是 ||none|
|Authorization|header|string| 否 ||none|
|body|body|[StudentDTO](#schemastudentdto)| 否 ||none|

> 返回示例

> 200 Response

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "username": "student",
    "password": "123456",
    "name": "li",
    "studentNo": null
  },
  "timestamp": 1748488342106
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|[ResultStudentDTO](#schemaresultstudentdto)|

## GET getCourse

GET /api/student/{username}/getCourse

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|username|path|string| 是 ||none|
|Authorization|header|string| 否 ||none|

> 返回示例

> 200 Response

```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 2,
      "courseName": "Java程序设计2",
      "majorId": 1,
      "grade": 1,
      "courseType": 1,
      "credit": 3,
      "isPublic": 1,
      "status": 1
    },
    {
      "id": 1,
      "courseName": "Java程序设计",
      "majorId": 1,
      "grade": 1,
      "courseType": 1,
      "credit": 3,
      "isPublic": 1,
      "status": 1
    }
  ],
  "timestamp": 1748491864986
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|[ResultListCourseDTO](#schemaresultlistcoursedto)|

## GET getAllCourse

GET /api/student/{username}/getAllCourse

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|Authorization|header|string| 否 ||none|

> 返回示例

> 200 Response

```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "courseName": "Java程序设计",
      "majorId": 1,
      "grade": 1,
      "courseType": 1,
      "credit": 3,
      "isPublic": 1,
      "status": 1
    },
    {
      "id": 2,
      "courseName": "Java程序设计2",
      "majorId": 1,
      "grade": 1,
      "courseType": 1,
      "credit": 3,
      "isPublic": 1,
      "status": 1
    },
    {
      "id": 3,
      "courseName": "Java程序设计3",
      "majorId": 1,
      "grade": 1,
      "courseType": 1,
      "credit": 3,
      "isPublic": 1,
      "status": 1
    },
    {
      "id": 4,
      "courseName": "Java程序设计4",
      "majorId": 1,
      "grade": 1,
      "courseType": 1,
      "credit": 3,
      "isPublic": 1,
      "status": 1
    }
  ],
  "timestamp": 1748491842552
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|[ResultListCourseDTO](#schemaresultlistcoursedto)|

## PUT chooseCourse

PUT /api/student/{username}/chooseCourse

> Body 请求参数

```json
2
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|username|path|string| 是 ||none|
|Authorization|header|string| 否 ||none|
|body|body|integer| 否 | 课程id|none|

> 返回示例

> 200 Response

```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 2,
      "courseName": "Java程序设计2",
      "majorId": 1,
      "grade": 1,
      "courseType": 1,
      "credit": 3,
      "isPublic": 1,
      "status": 1
    }
  ],
  "timestamp": 1748491716190
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|[ResultListCourseDTO](#schemaresultlistcoursedto)|

# TeacherController

## POST addCourse

POST /api/teacher/{username}/addCourse

> Body 请求参数

```json
{
  "courseName": "Java程序设计",
  "majorId": 1,
  "grade": 1,
  "courseType": 1,
  "credit": 3,
  "isPublic": 1
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|username|path|string| 是 ||none|
|Authorization|header|string| 否 ||none|
|body|body|[CourseDTO](#schemacoursedto)| 否 ||none|

> 返回示例

> 200 Response

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "courseNo": "CS001",
    "courseName": "Java程序设计",
    "teacherId": 1,
    "majorId": 1,
    "grade": 1,
    "courseType": 1,
    "credit": 3,
    "isPublic": 1,
    "status": 1,
    "createTime": "2025-05-29T11:26:07.0811467",
    "updateTime": "2025-05-29T11:26:07.0811467"
  },
  "timestamp": 1748489167102
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|[ResultCourse](#schemaresultcourse)|

## GET getCourse

GET /api/teacher/{username}/getAllCourse

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|username|path|string| 是 ||none|
|Authorization|header|string| 否 ||none|

> 返回示例

```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "courseName": "Java程序设计",
      "majorId": 1,
      "grade": 1,
      "courseType": 1,
      "credit": 3,
      "isPublic": 1,
      "status": 1
    }
  ],
  "timestamp": 1748489229759
}
```

```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "courseName": "Java程序设计",
      "majorId": 1,
      "grade": 1,
      "courseType": 1,
      "credit": 3,
      "isPublic": 1,
      "status": 1
    },
    {
      "id": 2,
      "courseName": "Java程序设计2",
      "majorId": 1,
      "grade": 1,
      "courseType": 1,
      "credit": 3,
      "isPublic": 1,
      "status": 1
    },
    {
      "id": 3,
      "courseName": "Java程序设计3",
      "majorId": 1,
      "grade": 1,
      "courseType": 1,
      "credit": 3,
      "isPublic": 1,
      "status": 1
    },
    {
      "id": 4,
      "courseName": "Java程序设计4",
      "majorId": 1,
      "grade": 1,
      "courseType": 1,
      "credit": 3,
      "isPublic": 1,
      "status": 1
    }
  ],
  "timestamp": 1748489303259
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|[ResultListCourseDTO](#schemaresultlistcoursedto)|

# 数据模型

<h2 id="tocS_ResultMapObject">ResultMapObject</h2>

```json
{
  "code": 0,
  "message": "string",
  "data": {
    "key": {}
  },
  "timestamp": 0
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|integer|false|none||响应码|
|message|string|false|none||响应消息|
|data|[MapObject](#schemamapobject)|false|none||响应数据|
|timestamp|integer(int64)|false|none||响应时间戳|

<h2 id="tocS_UserDTO">UserDTO</h2>

```json
{
  "username": "string",
  "password": "string",
  "role": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|username|string|false|none||none|
|password|string|false|none||none|
|role|string|false|none||none|

<h2 id="tocS_User">User</h2>

```json
{
  "id": 0,
  "username": "string",
  "password": "string",
  "role": "string",
  "status": 0,
  "createTime": "string",
  "updateTime": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|id|integer(int64)|false|none||none|
|username|string|false|none||none|
|password|string|false|none||none|
|role|string|false|none||none|
|status|integer|false|none||none|
|createTime|string|false|none||none|
|updateTime|string|false|none||none|

<h2 id="tocS_ResultUser">ResultUser</h2>

```json
{
  "code": 0,
  "message": "string",
  "data": {
    "id": 0,
    "username": "string",
    "password": "string",
    "role": "string",
    "status": 0,
    "createTime": "string",
    "updateTime": "string"
  },
  "timestamp": 0
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|integer|false|none||响应码|
|message|string|false|none||响应消息|
|data|[User](#schemauser)|false|none||响应数据|
|timestamp|integer(int64)|false|none||响应时间戳|

<h2 id="tocS_StudentDTO">StudentDTO</h2>

```json
{
  "username": "string",
  "password": "string",
  "name": "string",
  "studentNo": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|username|string|false|none||none|
|password|string|false|none||none|
|name|string|false|none||none|
|studentNo|string|false|none||none|

<h2 id="tocS_ResultStudentDTO">ResultStudentDTO</h2>

```json
{
  "code": 0,
  "message": "string",
  "data": {
    "username": "string",
    "password": "string",
    "name": "string",
    "studentNo": "string"
  },
  "timestamp": 0
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|integer|false|none||响应码|
|message|string|false|none||响应消息|
|data|[StudentDTO](#schemastudentdto)|false|none||响应数据|
|timestamp|integer(int64)|false|none||响应时间戳|

<h2 id="tocS_CourseDTO">CourseDTO</h2>

```json
{
  "id": 0,
  "courseName": "string",
  "majorId": 0,
  "grade": 0,
  "courseType": 0,
  "credit": 0,
  "isPublic": 0,
  "status": 0
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|id|integer(int64)|false|none||none|
|courseName|string|false|none||课程名称|
|majorId|integer(int64)|false|none||适用专业ID|
|grade|integer|false|none||适用年级|
|courseType|integer|false|none||课程性质(1-必修,2-选修)|
|credit|number|false|none||学分|
|isPublic|integer|false|none||是否公开(0-否,1-是)|
|status|integer|false|none||课程状态(1-已提交,2-审核通过,3-审核不通过,4-公开,5-隐藏)|

<h2 id="tocS_ResultListCourseDTO">ResultListCourseDTO</h2>

```json
{
  "code": 0,
  "message": "string",
  "data": [
    {
      "id": 0,
      "courseName": "string",
      "majorId": 0,
      "grade": 0,
      "courseType": 0,
      "credit": 0,
      "isPublic": 0,
      "status": 0
    }
  ],
  "timestamp": 0
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|integer|false|none||响应码|
|message|string|false|none||响应消息|
|data|[[CourseDTO](#schemacoursedto)]|false|none||响应数据|
|timestamp|integer(int64)|false|none||响应时间戳|

<h2 id="tocS_Course">Course</h2>

```json
{
  "id": 0,
  "courseNo": "string",
  "courseName": "string",
  "teacherId": 0,
  "majorId": 0,
  "grade": 0,
  "courseType": 0,
  "credit": 0,
  "isPublic": 0,
  "status": 0,
  "createTime": "string",
  "updateTime": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|id|integer(int64)|false|none||课程ID|
|courseNo|string|false|none||课程编号|
|courseName|string|false|none||课程名称|
|teacherId|integer(int64)|false|none||授课教师ID|
|majorId|integer(int64)|false|none||适用专业ID|
|grade|integer|false|none||适用年级|
|courseType|integer|false|none||课程性质(1-必修,2-选修)|
|credit|number|false|none||学分|
|isPublic|integer|false|none||是否公开(0-否,1-是)|
|status|integer|false|none||课程状态(1-已提交,2-审核通过,3-审核不通过,4-公开,5-隐藏)|
|createTime|string|false|none||创建时间|
|updateTime|string|false|none||更新时间|

<h2 id="tocS_ResultCourse">ResultCourse</h2>

```json
{
  "code": 0,
  "message": "string",
  "data": {
    "id": 0,
    "courseNo": "string",
    "courseName": "string",
    "teacherId": 0,
    "majorId": 0,
    "grade": 0,
    "courseType": 0,
    "credit": 0,
    "isPublic": 0,
    "status": 0,
    "createTime": "string",
    "updateTime": "string"
  },
  "timestamp": 0
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|integer|false|none||响应码|
|message|string|false|none||响应消息|
|data|[Course](#schemacourse)|false|none||响应数据|
|timestamp|integer(int64)|false|none||响应时间戳|

