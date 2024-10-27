import com.example.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {
    @Mock
    Feline feline;

    @Test
    public void getKittensReturnCorrectValue() throws Exception {
        Lion lion = new Lion("Самец", feline);
        Mockito.when(feline.getKittens()).thenReturn(1);
        int expectedKittensCount = 1;
        int actualKittensCount = lion.getKittens();
        Assert.assertEquals(expectedKittensCount, actualKittensCount);
    }
    @Test
    public void getFoodReturnCorrectValue() throws Exception {
        Lion lion = new Lion("Самец", feline);
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> actualFood = lion.getFood();
        Mockito.verify(feline).getFood("Хищник");
        System.out.println(actualFood);
        Assert.assertEquals(expectedFood, actualFood);}

    //@Test(expected = Exception.class)
    //public void getKittensThrowsException() throws Exception {
      //  Lion lion = new Lion("Самец", feline);
      //  Mockito.when(feline.getKittens()).thenThrow(new Exception("Ошибка получения котят"));
    // lion.getKittens(); // Здесь мы ожидаем выброса исключения

        @Test(expected = Exception.class)
        public void getFoodThrowsException() throws Exception {
            Lion lion = new Lion("Самец", feline);
            // Настройка мока, чтобы метод getFood выбрасывал исключение
            Mockito.when(feline.getFood("Хищник")).thenThrow(new Exception("Ошибка получения корма"));

            // Здесь мы ожидаем выброса исключения
            lion.getFood();
        }

        @Test(expected = Exception.class)
        public void lionConstructorThrowsExceptionForInvalidSex() throws Exception {
            // Пытаемся создать объект Lion с некорректным значением пола
            new Lion("Неправильное значение", feline);
        }

        @Test
        public void lionConstructorDoesNotThrowExceptionForValidSex() throws Exception {
            // Проверяем, что конструктор не выбрасывает исключение для корректных значений
            Lion maleLion = new Lion("Самец", feline);
            Lion femaleLion = new Lion("Самка", feline);
            Assert.assertTrue(maleLion.doesHaveMane());
            Assert.assertFalse(femaleLion.doesHaveMane());


        }

    }
}
