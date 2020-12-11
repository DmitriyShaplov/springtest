package ru.shaplov.spring.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.github.opendevl.JFlat;
import com.github.wnameless.json.flattener.JsonFlattener;

import org.junit.Test;

import ru.shaplov.spring.xml.dto.mypackage.ObjectFactory;

import lombok.Data;

@Data
public class JacksonDeserializationXmlTest {

    @Test
    @SuppressWarnings("unchecked")
    //чтение xml в объект средствами JAXB.
    public void deserializeXml() throws Exception {
        FileInputStream fs = new FileInputStream("C:\\Users\\dshaplov\\IdeaProjects\\springtest\\SpringTests\\src\\main\\resources\\Request5.xml");
        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        JAXBElement<ru.shaplov.spring.xml.dto.mypackage.EPGURequestType> requestType = (JAXBElement<ru.shaplov.spring.xml.dto.mypackage.EPGURequestType>) unmarshaller.unmarshal(fs);
        ru.shaplov.spring.xml.dto.mypackage.EPGURequestType value = requestType.getValue();

//        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.registerModule(new JaxbAnnotationModule());
//        EPGURequestType epguRequestType = xmlMapper.readValue(
//                String.join("", Files.readAllLines(Paths.get("C:\\Users\\dshaplov\\IdeaProjects\\springtest\\SpringTests\\src\\main\\resources\\Request5.xml"))),
//                EPGURequestType.class);
    }

    @Test
    public void flatJson() throws Exception {
        JFlat flat = new JFlat(String.join(System.lineSeparator(), Files.readAllLines(
                Paths.get("C:\\Users\\dshaplov\\Desktop\\подключения\\EPGURequest5.txt"))));
        flat.json2Sheet().write2csv("C:\\Users\\dshaplov\\Desktop\\подключения\\EPGURequest5.csv");
        ObjectMapper objectMapper = new ObjectMapper();


    }

    @Test
    public void readCsv() throws Exception {
        List<Map<String, String>> response = new LinkedList<Map<String, String>>();
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> iterator = mapper.reader(Map.class)
                .with(schema)
                .readValues(new File("C:\\Users\\dshaplov\\Desktop\\подключения\\EPGURequest5.csv"));
        while (iterator.hasNext()) {
            response.add(iterator.next());
        }
//        Map<String, String> stringStringMap = csvMapper.readValue(new File("C:\\Users\\dshaplov\\Desktop\\подключения\\EPGURequest5.csv"),
//                new TypeReference<Map<String, String>>() {
//                });
    }

    @Test
    public void flattenJson() throws Exception {
        String json = String.join(System.lineSeparator(), Files.readAllLines(Paths.get("C:\\Users\\dshaplov\\Desktop\\подключения\\EPGURequest5.txt")));
        String flatten = JsonFlattener.flatten(json);
    }

    @Test
    //Тупо валидация xsd схемы, упадет с ошибкой, если валидация не прошла.
    public void validateScheme() throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("C:\\Users\\dshaplov\\IdeaProjects\\springtest\\SpringTests\\src\\main\\resources\\xsd-inv-epgu_v1.0.xsd"));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File("C:\\Users\\dshaplov\\IdeaProjects\\springtest\\SpringTests\\src\\main\\resources\\Request5.xml")));

    }

    @Test
    public void testImpl() throws JsonProcessingException {
        CsvMapper mapper = new CsvMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);


        List<TestImpl> list = new ArrayList<>();
        list.add(new TestImpl());
        list.add(new TestImpl());
        list.add(new TestImpl());
        list.add(new TestImpl());

        CsvSchema schema = CsvSchema.builder().setUseHeader(true)
                .addColumn("name")
                .addColumn("year")
                .build().withColumnSeparator(';');

        ObjectWriter with = mapper.writerFor(new TypeReference<List<TestImpl>>() {
        }).with(schema);
        String s = with.writeValueAsString(list);
    }

    @Test
    public void annot() {
        String abc = "Приветульки";
        byte[] bytes = abc.getBytes(StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(aByte & 0xFF);
        }
        System.out.println(sb.toString().substring(0, 12));
    }

    @XmlElement
    private String name = "123";

    @Data
    private static class TestImpl {
        private String name = "na;;me";
        private int year = 21;
        
    }

    private static interface Test1{
        String getName();
        int getYear();
    }
}

