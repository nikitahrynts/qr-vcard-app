package by.nces.qr_vcard_app.service;

import by.nces.qr_vcard_app.model.Employee;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class EmployeeQRService {

    @Value("${app.qr.path}")
    private String PATH;

    @Value("${app.qr.size}")
    private int SIZE;

    @Value("${app.qr.version}")
    private int QR_VERSION;

    public void createVCard(Employee employee) {

        Map<EncodeHintType, Object> hints = new HashMap<>();
        String vCardData = getVCardData(employee);

        hints.put(EncodeHintType.QR_VERSION, QR_VERSION);
        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 0);

        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(vCardData,
                    BarcodeFormat.QR_CODE, SIZE, SIZE,
                    hints);
        } catch (WriterException e) {
            bitMatrix = new BitMatrix(100, 100);
        }

        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(PATH + employee.getFirstName() + "_" + employee.getLastName() + ".png");
        try {
            MatrixToImageWriter.writeToPath(bitMatrix, "png", file.toPath());
        } catch (IOException e) {
            MatrixToImageWriter.toBufferedImage(bitMatrix);
        }
    }

    private static String getVCardData(Employee employee) {

        return "BEGIN:VCARD\n" +
                "VERSION:3.0\n" +
                "N:" + employee.getLastName() + ";" + employee.getFirstName() + "\n" +
                "TEL;TYPE=cell:" + employee.getMobileNumber() + "\n" +
                "TEL;TYPE=work:" + employee.getWorkNumber() + "\n" +
                "TEL;TYPE=pcs:" + employee.getAltWorkNumber() + "\n" +
                "EMAIL;TYPE=internet:" + employee.getEmail() + "\n" +
                "ORG:" + "НЦЭУ" + "\n" +
                "TITLE:" + employee.getRole() + "\n" +
                "END:VCARD";
    }

    public void deleteContents(File file) {

        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            if (subFile.isDirectory()) {
                deleteContents(subFile);
            }
            subFile.delete();
        }
    }
}
