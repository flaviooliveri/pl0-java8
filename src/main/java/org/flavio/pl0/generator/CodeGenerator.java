package org.flavio.pl0.generator;

import java.util.List;

public class CodeGenerator {

    private List<Byte> content;

    public CodeGenerator(ContentInitializer initializer) {
        this.content = initializer.newContent();
    }

    public void movEax(String str) {
        Long value = Long.valueOf(str);
        movEax(value);
    }

    public void movEax(Long value) {
        content.add((byte)0xB8);
        addNumber(value);
    }

    public void call(Long value) {
        content.add((byte)0xE8);
        addNumber(value);
    }

    public void movEaxEdiPlusOffset(String str) {
        Long value = Long.valueOf(str);
        movEaxEdiPlusOffset(value);
    }

    public void movEaxEdiPlusOffset(Long offset) {
        content.add((byte)0x8B);
        content.add((byte)0x87);
        addNumber(offset);
    }

    public void movEdiPlusOffsetEax(Long offset) {
        content.add((byte)0x89);
        content.add((byte)0x87);
        addNumber(offset);
    }

    public void pushEax() {
        content.add((byte) 0x50);
    }

    public void popEax() {
        content.add((byte) 0x58);
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
        content.add((byte)0xF7);
        content.add((byte) 0xD8);
    }

    public void addEaxEbx() {
        content.add((byte)0x01);
        content.add((byte)0xD8);
    }

    public void subEaxEbx() {
        content.add((byte)0x29);
        content.add((byte)0xD8);
    }

    public void mulEaxEbx() {
        content.add((byte)0xF7);
        content.add((byte)0xEB);
    }

    public void divEaxEbx() {
        content.add((byte)0xF7);
        content.add((byte)0xFB);
    }

    public void cmpEaxEbx() {
        content.add((byte)0x39);
        content.add((byte)0xC3);
    }

    public void changeEaxEbx() {
        content.add((byte)0x93);
    }

    public void cdq() {
        content.add((byte)0x99);
    }


    public void addNumber(long value) {
        Byte[] number = generateNumber(value);
        content.add(number[0]);
        content.add(number[1]);
        content.add(number[2]);
        content.add(number[3]);
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
}
