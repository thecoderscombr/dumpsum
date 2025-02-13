package br.com.thecoders.dumpsum;

import javafx.application.Platform;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CheckSum {
    private static long f;

    public void perform(String path) throws IOException, NoSuchAlgorithmException {

        Platform.runLater(() -> {
            App.getController().tfSha1.clear();
            App.getController().tfSha256.clear();
            App.getController().btnCheckSum.setDisable(true);
            App.getController().btnOpen.setDisable(true);
        });


        f = 0;

        MessageDigest messageDigestSha1 = MessageDigest.getInstance("SHA-1");
        MessageDigest messageDigestSha256 = MessageDigest.getInstance("SHA-256");

        byte[] buf = new byte[1024];

        Path path1 = Paths.get(path);

        long l = path1.toFile().length() / 100_000;

        try (InputStream inputStream = Files.newInputStream(path1)) {
            int c;
            double i = 0.0;

            while ((c = inputStream.read(buf)) != -1) {

                i++;

                if (l > 0) {
                    if (i % l == 0) {
                        Platform.runLater(() -> App.getController().piProgress.setProgress((f += 1) * 0.01));
                    }
                }

                messageDigestSha1.update(buf, 0, c);
                messageDigestSha256.update(buf, 0, c);
            }
            Platform.runLater(() -> App.getController().piProgress.setProgress(1D));
        }

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();

        for (byte b : messageDigestSha1.digest()) {
            stringBuilder.append(String.format("%02x", b));
        }

        for (byte b : messageDigestSha256.digest()) {
            stringBuilder2.append(String.format("%02x", b));
        }


        Platform.runLater(() -> {
            App.getController().btnCheckSum.setDisable(false);
            App.getController().btnOpen.setDisable(false);
            App.getController().tfSha1.setText(stringBuilder.toString().toUpperCase());
            App.getController().tfSha256.setText(stringBuilder2.toString().toUpperCase());
        });
    }
}
