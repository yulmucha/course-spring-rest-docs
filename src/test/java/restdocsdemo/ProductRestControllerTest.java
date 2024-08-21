package restdocsdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
public class ProductRestControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .addFilter(new CharacterEncodingFilter("UTF-8", true))
//                .alwaysDo(MockMvcResultHandlers.print()) // 테스트 실행 후 요청과 응답의 상세 정보를 콘솔에 출력
                .apply(
                        documentationConfiguration(restDocumentation)
//                                .operationPreprocessors()
//                                .withRequestDefaults(Preprocessors.prettyPrint())  // 요청 JSON을 예쁘게
//                                .withResponseDefaults(Preprocessors.prettyPrint()) // 응답 JSON을 예쁘게
                )
                .build();
    }

    @Test
    public void getProductTest() throws Exception {
        this.mockMvc.perform(get("/api/products/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("product-get",
                        responseFields(
                                fieldWithPath("id").description("상품 ID입니다"),
                                fieldWithPath("name").description("상품 이름입니다"),
                                fieldWithPath("brand").description("상품 브랜드입니다"),
                                fieldWithPath("price").description("상품 가격입니다")
                        )
                ));
    }

    @Test
    public void createProductTest() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest("에어팟", "애플", 100);
        this.mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createProductRequest)))
                .andExpect(status().isOk())
                .andDo(document("product-create",
                        responseFields(
                                fieldWithPath("id").description("상품 ID입니다"),
                                fieldWithPath("name").description("상품 이름입니다"),
                                fieldWithPath("brand").description("상품 브랜드입니다"),
                                fieldWithPath("price").description("상품 가격입니다")
                        )
                ));
    }
}
