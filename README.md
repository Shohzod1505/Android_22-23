# Android_2022-23
SingleActivityApplication!!!

Экраном является фрагмент, активити выступает как контейнер.
- На экране первого фрагмента располагается три кнопки:
  -- Нажатие на первую кнопку переводит на второй фрагмент (при этом после перехода на второй экран, должна быть возможность вернуться к предыдущему экрану по нажатию на кнопку "Назад")
  --Нажатие на вторую кнопку  увеличивает значение переменной counter на +1, которая хранится в первом фрагменте. Значение counter-a отображается визуально на экране в textView, формат "Counter value: $counter".
  -- Нажатие на третью кнопку открывает DialogFragment c кастомным layout. Внутри находиться textInputLayout+editText. В editText можно ввести числовое значение от 0 до 100.
  При нажатии на positiveButton counter фрагмента увеличивается на это число. При нажатии на negativeButton диалог закрывается, при нажатии на neutralButton значение вычитается.
  Значение переменной counter не может быть отрицательным. При изменении конфигурации значение переменной counter должна сохраняться.
  При вводе числа больше 100, либо не числового значения, то в textInputLayout должна отображаться ошибка: "Не верный формат данных". Для этого у него есть метод setError

- На экране второго фрагмента у вас есть TextView, при открытии в нем отображается значение counter. Формат такой же как и на первом экране. В зависимости от значение, меняется цвет background экрана. от 0..50 - color1, 51..100 - color2, 100+ -> color3. Цвета на ваш выбор

Навигация должна сопровождаться анимацией.

* Доп. задание:
- добавить поддержку горизонтальной ориентации. Т.е. при вертикальной ориентации у вас должно сохраняться обычное поведение из основного задания, но при запуске на планшете не должно быть двух отдельных экрана. Экран дробится на 2 части, слева фрагмент1, справа фрагмент2. При нажатии кнопки навигации в левой части экрана, в правой части экрана значения должны изменяться

К пулл реквесту должно прилагаться видео

Ограничение: можно использовать только fragmentManager. При использовании navigation-component работа аннулируется. Обратите на это внимание! Почему так я объяснял в самом начале пары

Сроки 11.10 23:59
Баллы: 5 + 1(доп)
