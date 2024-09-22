#include <stdio.h>
#include <stdlib.h>
#define _CRT_SECURE_NO_DEPRECATE
#include <math.h>

//����1����: �������� ��� ����� �� ����� ��� ���� ����� ����� ��� ����� �� ������� �� ���� ����� ���� �����
// parmeters :
    //n - the number to check
int perfectnum(int n) {
    //Variable  declaration
    int sum = 1;
    //code section
    for (int i = 2; i <= sqrt(n); i++) {
        if (n % i == 0) {
            sum += i + (n / i);
        }
    }
    if (sum == n) {
        return 1;
    }
    else {
        return 0;
    }
}

//����2 ����:������� ��� ����� ��� ����� ���� ����� ����� �1 ��� ���� �������
//parmeters:
    // n - the number to check
int Collatzsequence(int n) {
    //Variable  declaration
    int steps = 0;
    //code section
    while (log2(n) - (int)(log2(n)) != 0.0) {
        if (n % 2 == 0) {
            steps++;
            n /= 2;
        }
        else {
            steps++;
            n = n * 3 + 1;
        }
    }
    return steps += log2(n);
}

//����3 ����: ���� ������ �� ����� ��� �� ��� ������ ������
void SumPai() {
    //Variable  declaration
    int change = 1;
    float sum = 0;
    int i = 1;
    //code section
    while ((4.0 / i) > 0.00001) {
        sum += change * (4.0 / i);
        change *= -1;
        i += 2;
    }
    printf("%f %d", sum, i);
}

//�����4 ����: ������� ��� ������� �� ���� ��� ���� �� 1 ������ ���
void longestone() {
    //Variable  declaration
    int lengthMax = 0, length = 0, num;
    //code section
    do {
        printf("enter yout number here:");
        scanf("%d", &num);
        if (num == 1) {
            length = length * 10 + 1;
        }
        else if (num != 1) {
            if (length > lengthMax) {
                lengthMax = length;
            }
            length = 0;
        }
    } while (num == 1 || num == 0);

    printf("%d\n", lengthMax);
}

//����5 ����: ������� ��� ������ �� ����� ������� �� ����� ��� ���� ��� ������ �����
void  biggest3digit() {
    //Variable  declaration
    int max1 = 0, max2 = 0, max3 = 0, num1 = 0, num2 = 0, num3 = 0;
    //code section
    do {
        printf("enter your number here: ");
        max3 = max2;
        max2 = max1;
        scanf("%d", &max1);
        if (max1 + max2 + max3 > num1 + num2 + num3) {
            num1 = max1;
            num2 = max2;
            num3 = max3;
        }
    } while (max1 > 0);

    printf("%d %d %d\n", num1, num2, num3);
}

//����6 ����: ������� ��� ����� ���� ������ ������� ���� ����� ���
void BinaryToDec() {
    //Variable  declaration
    long binary;
    int number = 0;
    int bit = 1;
    //code section
    printf("enter your binary number here:");
    scanf("%ld", &binary);
    while (binary > 0) {
        int lastdigit = binary % 10;
        number += bit * lastdigit;
        bit *= 2;
        binary /= 10;
    }
    printf("%d\n", number);
}

//����7 ����: ������� ��� ����� ���� ����� ������ �� ����� 2.719�� ������ �� ���� ������
void atesert(float epsilon) {
    //Variable  declaration
    int sum = 1;
    float e = 1;
    int i = 1;
    //code section
    while ((1.0 / sum) >= epsilon) {
        sum *= i;
        e += (float)(1.0 / sum);
        i++;
    }
    printf("%f\n", e);
}
//����: ��� ��� ���� ������ �� ��� ���������
int main(int argc, char* argv[]) {
    //code section
    int y = perfectnum(6);
    printf("%d\n", y);
    int x = Collatzsequence(30);
    printf("%d\n", x);
    longestone();
    BinaryToDec();
    biggest3digit();
    atesert(0.001);
    return 0;
}