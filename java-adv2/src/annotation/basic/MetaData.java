package annotation.basic;

@AnnoMeta
public class MetaData {

//    @AnnoMeta
    private String id;

    @AnnoMeta
    public void call() {
    }

    public static void main(String[] args) throws NoSuchMethodException {
        final AnnoMeta typeAnno = MetaData.class.getAnnotation(AnnoMeta.class);
        System.out.println("typeAnno = " + typeAnno);

        final AnnoMeta methodAnno = MetaData.class.getMethod("call").getAnnotation(AnnoMeta.class);
        System.out.println("methodAnno = " + methodAnno);
    }
}
