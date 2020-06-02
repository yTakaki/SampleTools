package com.example.demo.manage.domain.repository.mybatis;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import com.example.demo.manage.domain.model.Item;
import com.example.demo.manage.domain.repository.ItemMapper;

@MybatisTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ItemMapperTest {

	@Autowired
	private ItemMapper mapper;

	@Autowired
	private NamedParameterJdbcOperations jdbc;

	@Test
	void insertItemTest() throws Exception {
		{
			// setup
			Item item = new Item("99999999","TEST","testcomponent",true,0,
					"01","02","03","04","05","06","07","08","09","10");
			// execute
			mapper.deleteItem("99999999");
			mapper.insertItem(item);
			Item actual = jdbc.queryForObject("SELECT * FROM item WHERE item_id = :item_id",
					new MapSqlParameterSource("item_id",item.getItemId()),
					new BeanPropertyRowMapper<>(Item.class));
			// assertion
			assertThat(actual.getItemCd(),is("TEST"));
			assertThat(actual.getItemName(),is("testcomponent"));
			assertThat(actual.isCompositeFlag(),is(true));
			assertThat(actual.getItemStatus(),is(0));
			assertThat(actual.getComp1(),is("01"));
			assertThat(actual.getComp2(),is("02"));
			assertThat(actual.getComp3(),is("03"));
			assertThat(actual.getComp4(),is("04"));
			assertThat(actual.getComp5(),is("05"));
			assertThat(actual.getComp6(),is("06"));
			assertThat(actual.getComp7(),is("07"));
			assertThat(actual.getComp8(),is("08"));
			assertThat(actual.getComp9(),is("09"));
			assertThat(actual.getComp10(),is("10"));
		}
	}
	/*
	@Test
	@Sql(statements= {
			"DELETE FROM component",
			"INSERT INTO component VALUES ('99999999','TEST','testcomponent',true,0)"
	})
	void selectOneComponentTest() throws Exception {
		{
			// setup
			String id = "99999999";
			// execution
			Component actual = mapper.selectOneComponent(id);
			// assertion
			assertThat(actual.getComponentId(),is("99999999"));
			assertThat(actual.getComponentCd(),is("TEST"));
			assertThat(actual.getComponentName(),is("testcomponent"));
			assertThat(actual.isFoodFlag(),is(true));
			assertThat(actual.getComponentStatus(),is(0));
		}
	}

	@Test
	@Sql(statements= {
			"DELETE FROM component",
			"INSERT INTO component VALUES ('99999999','TEST','testcomponent',true,0)"
	})
	void selectAllComponentTest() throws Exception {
		{
			// execution
			List<Component> actual = mapper.selectAllComponent();
			// assertion
			assertThat(actual.size(),is(1));
			assertThat(actual.get(0).getComponentId(),is("99999999"));
			assertThat(actual.get(0).getComponentCd(),is("TEST"));
			assertThat(actual.get(0).getComponentName(),is("testcomponent"));
			assertThat(actual.get(0).isFoodFlag(),is(true));
			assertThat(actual.get(0).getComponentStatus(),is(0));
		}
	}

	@Test
	@Sql(statements= {
			"DELETE FROM component",
			"INSERT INTO component VALUES ('99999999','TEST','testcomponent',true,0)"
	})
	void updateComponentTest() throws Exception {
		{
			// setup : component name is change
			Component comp = new Component("99999999","TEST","testcomp",true,0);
			// execution
			mapper.updateComponent(comp);
			Component actual = jdbc.queryForObject("SELECT * FROM component WHERE component_id = :component_id",
					new MapSqlParameterSource("component_id",comp.getComponentId()),
					new BeanPropertyRowMapper<>(Component.class));
			// assertion
			assertThat(actual.getComponentName(),is("testcomp"));
		}
	}

	@Test
	@Sql(statements= {
			"DELETE FROM component",
			"INSERT INTO component VALUES ('99999999','TEST','testcomponent',true,0)"
	})
	void deleteComponentTest() throws Exception {
		{
			// setup
			String id = "99999999";
			// execution
			boolean actual = mapper.deleteComponent(id);
			// assertion
			assertThat(actual,is(true));
			assertThat(mapper.selectOneComponent(id),is(nullValue()));
		}
	}

	@Test
	@Sql(statements= {
			"DELETE FROM component",
			"INSERT INTO component VALUES ('99999999','TEST','testcomponent',true,0)"
	})
	void searchComponentTest() throws Exception {
		{
			String id = "9999";
			String cd = "TES";
			String name = "test";
			boolean flag = true;
			int status = 0;
			List<Component> actual = mapper.searchComponent(id,cd,name,flag,status);
			assertThat(actual.size(),is(1));
			assertThat(actual.get(0).getComponentId(),is("99999999"));
			assertThat(actual.get(0).getComponentCd(),is("TEST"));
			assertThat(actual.get(0).getComponentName(),is("testcomponent"));
			assertThat(actual.get(0).isFoodFlag(),is(true));
			assertThat(actual.get(0).getComponentStatus(),is(0));
		}
	}

	@Test
	@Sql(statements= {
			"DELETE FROM component",
			"INSERT INTO component VALUES ('99999999','TEST','testcomponent',true,0)"
	})
	void deleteAllComponentTest() throws Exception {
		Boolean actual = mapper.deleteAllComponent();
		assertThat(actual,is(true));
		assertThat(mapper.selectAllComponent().size(),is(0));
	}
	*/
}
