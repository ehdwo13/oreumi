import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ContactManager {
    private List<Contact> contacts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n1. 비즈니스 연락처 추가");
            System.out.println("2. 개인 연락처 추가");
            System.out.println("3. 연락처 출력");
            System.out.println("4. 연락처 검색");
            System.out.println("5. 종료");
            System.out.print("메뉴를 선택해주세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBusinessContact();
                    break;
                case 2:
                    addPersonalContact();
                    break;
                case 3:
                    displayContacts();
                    break;
                case 4:
                    searchContact();
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 메뉴를 선택해주세요.");
            }
        }
    }

    private void addBusinessContact() {
        System.out.print("이름을 입력하세요: ");
        String name = scanner.nextLine();
        System.out.print("전화번호를 입력하세요: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("회사명을 입력하세요: ");
        String company = scanner.nextLine();

        contacts.add(new BusinessContact(name, phoneNumber, company));
        System.out.println("비즈니스 연락처가 추가되었습니다.");
    }

    private void addPersonalContact() {
        System.out.print("이름을 입력하세요: ");
        String name = scanner.nextLine();
        System.out.print("전화번호를 입력하세요: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("관계를 입력하세요: ");
        String relation = scanner.nextLine();

        contacts.add(new PersonalContact(name, phoneNumber, relation));
        System.out.println("개인 연락처가 추가되었습니다.");
    }

    private void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("연락처가 비어있습니다.");
            return;
        }
        for (Contact contact : contacts) {
            contact.display();
        }
    }

    private void searchContact() {
        System.out.print("검색할 이름을 입력하세요: ");
        String name = scanner.nextLine();

        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contact.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("연락처를 찾을 수 없습니다.");
        }
    }
}