package in.gore.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Demonstrates the two ways in which avro message can be serialized
 * 1. with schema in the message
 * 2. without schema in the message.
 *
 * See {@link Reader} for implication of writing with and without schema.
 */
public class Writer {

    public static void main(String args[]) throws IOException {
        Schema schema = new Schema.Parser().parse(
                new File(Writer.class.getClassLoader().getResource("user.avsc").getFile()));

        GenericRecord user = new GenericData.Record(schema);
        user.put("name", "mihir");
        user.put("age", 53);
        user.put("dob", "01-1-1969");

        // write with schema
        File output = new File("with-schema.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(schema,output);
        dataFileWriter.append(user);
        dataFileWriter.close();

        // write without schema
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Encoder encoder = EncoderFactory.get().binaryEncoder(baos, null);
        datumWriter.write(user, encoder);
        encoder.flush();
        baos.flush();
        FileOutputStream out = new FileOutputStream("without-schema.avro");
        baos.writeTo(out);
    }
}
