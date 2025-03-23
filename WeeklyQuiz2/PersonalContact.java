class PersonalContact extends Contact {
    private String relation;

    public PersonalContact(String name, String phoneNumber, String relation) {
        super(name, phoneNumber);
        this.relation = relation;
    }

    @Override
    public void display() {
        System.out.println("이름: " + name + ", 전화번호: " + phoneNumber + ", 관계: " + relation);
    }
}