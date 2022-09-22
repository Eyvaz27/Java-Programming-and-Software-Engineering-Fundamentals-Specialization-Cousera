/**
 * Write a description of VigenereTester here.
 * 
 * @author Eyvaz Najafli
 * @version 09/20/2022
 */

import edu.duke.*;
import java.util.*;

public class VigenereTester {
    public void CaesarCipherTester() {
        FileResource fr = new FileResource();
        String messageSample = fr.asString();
        CaesarCipher sample = new CaesarCipher(3);
        String encryptedMessage = sample.encrypt(messageSample);
        String decryptedMessage = sample.decrypt(encryptedMessage);
        System.out.println("Original Message : "+messageSample);
        System.out.println("Encrypted Message : "+encryptedMessage);
        System.out.println("Decrypted Message : "+decryptedMessage);
    }
    public void CaesarCrackerTester() {
        FileResource fr = new FileResource();
        String messageSample = fr.asString();
        CaesarCracker sampleCracker = new CaesarCracker();
        int encryptionKey = sampleCracker.getKey(messageSample);
        System.out.println("Estimated Key : "+encryptionKey);
        System.out.println("Encrypted Message : "+messageSample);
        System.out.println("Decrypted Message : "+
                           sampleCracker.decrypt(messageSample));
    }
    public void VigenereCipherTester() {
        int[] keySet = {17, 14, 12, 4};
        FileResource fr = new FileResource();
        String messageSample = fr.asString();
        VigenereCipher sampleCipher = new VigenereCipher(keySet);
        String encryptedMessage = sampleCipher.encrypt(messageSample);
        System.out.println("Encrypted Message : "+encryptedMessage);
        System.out.println("Decrypted Message : "+
                           sampleCipher.decrypt(encryptedMessage));
    }
    public void VigenereBreakerTester() {
        FileResource fr = new FileResource();
        String encryptedMessage = fr.asString();
        VigenereBreaker sampleBreaker = new VigenereBreaker();
        int[] decryptionKeys = sampleBreaker.tryKeyLength(encryptedMessage, 5, 'e');
        String keyString = Arrays.toString(decryptionKeys);
        System.out.println("Encrypted Message : "+encryptedMessage);
        System.out.println("Decryption Keys : "+keyString);
    }
}
