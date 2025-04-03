src/test/java/restdocsdemo/ProductRestControllerTest.java
테스트 코드가 실행되면 문서에 필요한 스니펫들을 자동으로 생성

src/docs/asciidoc/index.adoc
생성된 스니펫들을 adoc 파일에서 활용하여 API 문서 작성 (애플리케이션 실행 시 index.html로 변환됨)

애플리케이션 실행 시 build/docs/asciidoc/ 경로에 index.html 파일이 생성됨

이제 브라우저에서 http://localhost:8080/docs/index.html 에 접근하면 문서를 볼 수 있음
