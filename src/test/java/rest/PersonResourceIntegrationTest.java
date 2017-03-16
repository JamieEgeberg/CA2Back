package rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Person;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

/**
 * Created by Niki on 2017-03-15.
 *
 * @author Niki
 */
public class PersonResourceIntegrationTest {

    private static Gson gson;

    @BeforeClass
    public static void setUpClass() throws Exception {
        gson = new Gson();
        String port = System.getProperty("server.port");
        if (port == null) RestAssured.port = 8080;
        else RestAssured.port = Integer.valueOf(port);

        String basePath = System.getProperty("server.base");
        if (basePath == null) basePath = "/api";
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if (baseHost == null) baseHost = "http://localhost";
        RestAssured.baseURI = baseHost;
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void pingTest() throws Exception {
        System.out.println("pingTest()");
        given().when().get("/person").then().statusCode(200);
    }

    @Test
    public void getPersonsTest() throws Exception {
        System.out.println("getPersonsTest()");
        String json = given()
                .when().get("/person")
                .body().asString();
        System.out.println("getPersonsTest() JSON: " + json);
        List<Person> list = getListFromJson(json);
        if (!json.equals("[]")) {
            assertTrue("list should not be null, list is: " + list,
                       list != null);
            assertTrue("list.Size() should be above 0, but is: " + list.size(),
                       list.size() > 0);
            Person person = list.get(0);
            assertTrue("person should not be null, person is: " + person,
                       person != null);
        } else {
            assertTrue("list.Size() should be 0, but is: " + list.size(),
                       list.size() == 0);
        }
    }

    @Test
    public void postPersonTest() throws Exception {
        System.out.println("postPersonTest()");
        Person p1 = new Person("FirstName",
                               "LastName",
                               "Clearly@AnEmail.com");
        p1.hobbies = null;
        p1.phones = null;
        String json = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(p1))
                .when().post("/person")
                .body().asString();
        System.out.println("postPersonTest() JSON: " + json);
        Person p2 = gson.fromJson(json, Person.class);
        assertTrue(p2.getId() > 0);
        assertTrue(p1.getFirstName().equals(p2.getFirstName()));
        assertTrue(p1.getLastName().equals(p2.getLastName()));
        assertTrue(p1.getEmail().equals(p2.getEmail()));
    }

    /**
     * Get a List of persons from Json
     *
     * @param json json array of person objects
     * @return List<Person>
     */
    private List<Person> getListFromJson(String json) {
        Type type = new TypeToken<List<Person>>() {}.getType();
        return gson.fromJson(json, type);
    }

}