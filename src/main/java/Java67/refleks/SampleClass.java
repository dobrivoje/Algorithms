package Java67.refleks;

@CustomAnnotation(name = "SampleClass",  value = "Sample Class Annotation")
public class SampleClass {

    @CustomAnnotation(name="sampleClassField",  value = "Sample Field Annotation")
    public String sampleField;

    public String getSampleField() {
        return sampleField;
    }

    public void setSampleField(String sampleField) {
        this.sampleField = sampleField;
    }
}
