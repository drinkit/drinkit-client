package ua.kiev.naiv.it;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.kiev.naiv.drinkit.cocktail.common.WebContextFilter;
import ua.kiev.naiv.drinkit.cocktail.search.Criteria;
import ua.kiev.naiv.drinkit.springconfig.AppConfig;
import ua.kiev.naiv.drinkit.springconfig.WebConfig;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author pkolmykov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
public class RecipeSearchIT {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = webAppContextSetup(this.context)
				.addFilter(new WebContextFilter())
				.build();
	}

	@Test
	public void test() throws Exception {
		String criteriaJson = "{\"cocktailTypes\": [1], \"ingredients\": [2, 3], \"options\": [2]}";
		String content = mockMvc.perform(get("/cocktail/search").param("criteria", criteriaJson))
				.andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();
		JsonNode jsonNode = new ObjectMapper().readTree(content);
		Assert.assertSame(1, jsonNode.size());
		return;
	}

	@Test
	public void getIngredientsTest() throws Exception {
		mockMvc.perform(get("/cocktail/getIngredients")).andExpect(status().isOk());
	}


}
