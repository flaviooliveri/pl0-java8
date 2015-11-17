package org.flavio.pl0.generator;

import java.util.List;

public class CodeGenerator {

    public static final int HEADER_SIZE = 224;
    private final int variableIndex;
    private List<Byte> content;

    public CodeGenerator(ContentInitializer initializer) {
        this.content = initializer.newContent();
        this.variableIndex = getContentSize();
    }

    public String getVariableAsHex() {
        return bytesToHex(getVariable());
    }

    public String getAsHex() {
        return bytesToHex(content);
    }

    public List<Byte> getVariable() {
        return content.subList(variableIndex, getContentSize());
    }

    public static String bytesToHex(List<Byte> in) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : in) {
            builder.append(String.format("%02x", b).toUpperCase()).append(" ");
        }
        return builder.toString().trim();
    }


    public void movEax(String str) {
        Long value = Long.valueOf(str);
        movEax(value);
    }

    public void movEax(Long value) {
        content.add((byte) 0xB8);
        addNumber(value);
    }

    public void movEcx(Long value) {
        content.add((byte) 0xB9);
        addNumber(value);
    }

    public void movEdi(Long value) {
        content.add((byte) 0xBF);
        addNumber(value);
    }

    public void movEdx(Long value) {
        content.add((byte) 0xBA);
        addNumber(value);
    }

    public void call(Long value) {
        content.add((byte) 0xE8);
        addNumber(value);
    }

    public void movEaxEdiPlusOffset(Long offset) {
        content.add((byte) 0x8B);
        content.add((byte) 0x87);
        addNumber(offset);
    }

    public void movEdiPlusOffsetEax(Long offset) {
        content.add((byte) 0x89);
        content.add((byte) 0x87);
        addNumber(offset);
    }

    public void pushEax() {
        content.add((byte) 0x50);
    }

    public void popEax() {
        if (content.get(getContentSize() - 1) == 0x50) {
            content.remove(getContentSize() - 1);
        } else {
            content.add((byte) 0x58);
        }
    }

    public void testAl(byte value) {
        content.add((byte) 0xA8);
        content.add(value);
    }

    public void jpo(byte value) {
        content.add((byte) 0x7B);
        content.add(value);
    }

    public void jmp(long value) {
        content.add((byte) 0xE9);
        addNumber(value);
    }

    public void popEbx() {
        content.add((byte) 0x5B);
    }

    public void negEax() {
        content.add((byte) 0xF7);
        content.add((byte) 0xD8);
    }

    public void addEaxEbx() {
        content.add((byte) 0x01);
        content.add((byte) 0xD8);
    }

    public void subEaxEbx() {
        content.add((byte) 0x29);
        content.add((byte) 0xD8);
    }

    public void mulEaxEbx() {
        content.add((byte) 0xF7);
        content.add((byte) 0xEB);
    }

    public void divEaxEbx() {
        content.add((byte) 0xF7);
        content.add((byte) 0xFB);
    }

    public void cmpEaxEbx() {
        content.add((byte) 0x39);
        content.add((byte) 0xC3);
    }

    public void changeEaxEbx() {
        content.add((byte) 0x93);
    }

    public void cdq() {
        content.add((byte) 0x99);
    }

    public void ret() {
        content.add((byte) 0xC3);
    }


    public void addNumber(long value) {
        Byte[] number = generateNumber(value);
        content.add(number[0]);
        content.add(number[1]);
        content.add(number[2]);
        content.add(number[3]);
    }

    public long getNumberAt(int index) {
        return getUnsignedByteAt(index + 3) * 16777216L + getUnsignedByteAt(index + 2) * 65536L
                + getUnsignedByteAt(index + 1) * 256L + getUnsignedByteAt(index);
    }

    private long getUnsignedByteAt(int index) {
        int i = content.get(index);
        if (i < 0)
            return 256 + i;
        return i;
    }

    private Byte[] generateNumber(Long value) {
        byte first, second, third, fourth;
        if (value < 0) {
            value = value + 4294967296L;
        }
        first = (byte) (value / 16777216);
        value = value % 16777216;
        second = (byte) (value / 65536);
        value = value % 65536;
        third = (byte) (value / 256);
        fourth = (byte) (value % 256);
        return new Byte[]{fourth, third, second, first};
    }

    public long distanceTo(long position) {
        return position - (getContentSize() + 5);
    }

    public void readln(long varPos) {
        long readlnPosition = 784;
        call(distanceTo(readlnPosition));
        movEdiPlusOffsetEax(varPos);
    }

    public void write(String value) {
        long writePosition = 368;
        int addressPosition = 193;
        int numberOfInstruccionBytes = 20;
        long stringPosition = getNumberAt(addressPosition) + numberOfInstruccionBytes + getContentSize() - HEADER_SIZE;
        movEcx(stringPosition);
        movEdx((long) value.length());
        call(distanceTo(writePosition));
        jmp(value.length());
        addString(value);
    }

    public void write() {
        popEax();
        int writePosition = 400;
        call(distanceTo(writePosition));
    }

    public void writeNewLine() {
        int writelnPosition = 384;
        call(distanceTo(writelnPosition));
    }

    private void addString(String value) {
        for (int i = 0; i < value.length(); i++)
            addByte((byte) value.charAt(i));
    }

    public void addByte(byte value) {
        content.add(value);
    }

    public void fixUp(int position, long value) {
        Byte[] number = generateNumber(value);
        content.set(position, number[0]);
        content.set(position + 1, number[1]);
        content.set(position + 2, number[2]);
        content.set(position + 3, number[3]);
    }

    public int getContentSize() {
        return content.size();
    }

    public void removeLastJump() {
        for (int i = 0; i < 5; i++)
            this.content.remove(this.getContentSize() - 1);
    }

    public void fixHeader() {
        this.fixUp(68, getContentSize());
        this.fixUp(72, getContentSize());
        this.fixUp(201, getContentSize() - HEADER_SIZE);
    }

    public List<Byte> getContent() {
        return content;
    }
}
