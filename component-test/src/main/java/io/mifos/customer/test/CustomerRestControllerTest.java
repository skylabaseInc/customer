/*
 * Copyright 2017 The Mifos Initiative.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mifos.customer.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mifos.customer.service.rest.controller.CustomerRestController;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by isaackamga on 07/09/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CustomerRestController.class)
@AutoConfigureRestDocs(outputDir = "$projectDir/component-test/build/snippets")
@SpringBootTest
public class CustomerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/snippets");

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    // Testing Get-A-Unique-Customer findCustomer()
    @Test
    public void findCustomerTest() throws Exception {
        this.mockMvc.perform(get("/customer/v1/customers/{identifier}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("home", pathParameters( parameterWithName("identifier")
                .description("Customer's Unique Identifier"))));
    }

    // Testing Get-A-List-Of-Customers fetchCustomer()
    @Test
    public void fetchCustomersTest() throws Exception {
        this.mockMvc.perform(get("/customer/v1/customers")
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andDo(document("home", pathParameters(parameterWithName("identifier")
                .description("A List Of Customer"))));
    }
}
