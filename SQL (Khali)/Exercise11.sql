/* הציגו את שמות העובדים ממויינים לפי שם משפחה בסדר עולה ולפי שם פרטי בסדר יורד */  
select FirstName, LastName from employees order by LastName asc, FirstName desc;

/*הנהלת החברה החליטה לתת בונוס 10% לעובדים שמשכורתם מתחת ל – 10000. */
select first_name, last_name, salary, salary * 0.1 as bonus
from employees 
where salary < 10000;

/* 2.    יש להוציא את כל מספרי ההזמנות שבוצעו ברבעון השלישי של השנה (בין חודשים 6-8 כולל)*/

/* הציגו את ממוצע העובדים של כל מחלקה אשר כמות העובדים בה גדול מ- 1*/
/* ושם המחלקה מסתיים ב- g */