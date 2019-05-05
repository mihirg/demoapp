package in.gore.avro;

import com.google.common.io.Files;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;

import java.io.File;
import java.io.IOException;

/**
 * Demonstrates reading a serialized avro message. See {@link Writer} for the different ways of serializing the
 * avro message
 * 1. If avro message is serialized with schema, we can choose to read it without specifying the schema or we can
 * apply our own schema. Applying our own schema has some rules.
 * 2. If no schema is present in file, then we need to provide a schema without which we will not be able to read.
 * Not serializing the schema in the avro message has a serious limitation, we can correct deserialize this file only if
 * we have the exact schema. If we dont have the exact schema, then changing ordering of fields will cause problems
 */
public class Reader {

    public static void main(String args[]) throws IOException {
        // this will work if the file has a schema in it. so we read based on the schema.
        // alternatively we can provide the schema, then the schema we provide will matched with schema
        // in the data file
        Schema schema = new Schema.Parser().parse(
                new File(Reader.class.getClassLoader().getResource("reader.avsc").getFile()));

        DatumReader<GenericRecord> datumReaderWithoutSchema = new GenericDatumReader<>(schema);
        File file = new File("with-schema.avro");
        DataFileReader<GenericRecord> reader = new DataFileReader<>(file, datumReaderWithoutSchema);
        while (reader.hasNext()) {
            GenericRecord record = reader.next();
            System.out.println(record);
        }

        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);
        byte[] inputBytes = Files.toByteArray(new File("without-schema.avro"));
        BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(inputBytes, null);
        GenericRecord read = datumReader.read(null, binaryDecoder);
        System.out.println(read);
    }
}
