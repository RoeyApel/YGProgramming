#include <stdio.h>

typedef struct
{
    char code[4];
    int number;
} PhoneNumber;

typedef struct
{
    char name[20];
    PhoneNumber phoneNumbers[3];
    char email[30];
} ContactInfo;

void getPhoneNumber(PhoneNumber *phoneNumber);
void printPhoneNumber(PhoneNumber *phoneNumber);
void getContactInfo(ContactInfo *contactInfo);
void printContactInfo(ContactInfo *contactInfo);

int main()
{
    ContactInfo contactInfo = {0};
    getContactInfo(&contactInfo);
    printContactInfo(&contactInfo);
    return 0;
}
void getContactInfo(ContactInfo *contactInfo)
{
    printf("name: ");
    scanf("%s", &contactInfo->name);

    printf("email: ");
    scanf("%s", &contactInfo->email);

    PhoneNumber *phoneNumbers = contactInfo->phoneNumbers;
    int i = 0;
    char userInput;

    do
    {
        getPhoneNumber(phoneNumbers + i);
        printf("Wanna put another one (Y/N): ");
        getchar();
        scanf("%c", &userInput);
        getchar();
        i++;
    } while (userInput != 'N' && i < 3);
}

void printContactInfo(ContactInfo *contactInfo)
{
    printf("name: %s\nemail: %s\n", contactInfo->name, contactInfo->email);
    PhoneNumber *phoneNumbers = contactInfo->phoneNumbers;
    int i = 0;

    while ((phoneNumbers + i)->number != 0 && i < 3)
    {
        printPhoneNumber(phoneNumbers + i);
        printf('\n');
        i++;
    }
}

void getPhoneNumber(PhoneNumber *phoneNumber)
{
    printf("code: ");
    scanf("%s", &phoneNumber->code);

    printf("number: ");
    scanf("%d", &phoneNumber->number);
}

void printPhoneNumber(PhoneNumber *phoneNumber)
{
    printf("Phone Number: %s-%d", phoneNumber->code, phoneNumber->number);
}