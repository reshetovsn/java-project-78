package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    @Test
    public void testString() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(5)).isFalse();

        schema.required();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("some text for check")).isTrue();

        schema.minLength(3);
        assertThat(schema.isValid("12")).isFalse();
        assertThat(schema.isValid("123")).isTrue();

        assertThat(schema.contains("some").isValid("some text for check")).isTrue();
        assertThat(schema.contains("sometext").isValid("some text for check")).isFalse();

        assertThat(schema.isValid("some text for check")).isFalse();
    }

    @Test
    public void testNumber() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("5")).isFalse();
        assertThat(schema.isValid(-10)).isTrue();

        assertThat(schema.positive().isValid(null)).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(-10)).isFalse();
        assertThat(schema.isValid(0)).isFalse();

        schema.range(5, 10);
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
    }

    @Test
    public void testMap() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("5")).isFalse();
        assertThat(schema.isValid(Map.of("key", "data"))).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        schema.sizeof(2);
        assertThat(schema.isValid(data)).isFalse();
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue();
    }
}
