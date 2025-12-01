class Main {
    public static void main(String[] args) {
        System.out.println(new Article().id);
        System.out.println(new Article().id);
        System.out.println(new Article().id);
        System.out.println(new Article().id);
    }
}

class Article {
    static int lastId;
    int id;

    static {
        lastId = 0;
    }

    Article() {
        this(lastId+1);
        lastId++;
    }

    Article(int id) {
        this.id = id;
    }
}