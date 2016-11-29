package edu.osu.cse5236.group9.dieta;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class DietaUnitTest {
    Meal mMeal;
    Food mFood;

    @Mock
    Context mMockContext;

    @Test
    public void addFoodTest() throws Exception {
        mMeal = new Meal();
        mMeal.addFood(new Food("apple"));
        mMeal.addFood(new Food("chinese noodle"));
        mMeal.addFood(new Food("chicken wings"));
        assertThat(mMeal.getFoods().size(),is(3));
        assertEquals(mMeal.getFoods().get(2).getName(),"chicken wings");
    }

    @Test
    public void sumUpTest() throws Exception {
        mFood = new Food("Total nutrition");
        mMeal = new Meal();
        mMeal.addFood(new Food(""));
        mMeal.addFood(new Food(""));
        mMeal.addFood(new Food(""));
        for (Food curfood : mMeal.getFoods()) {
            double temp = Math.random()*100;
            curfood.setCalories(temp);
            mFood.setCalories(mFood.getCalories()+temp);
            temp = Math.random()*100;
            curfood.setTotal_Fat(temp);
            mFood.setTotal_Fat(mFood.getTotal_Fat()+temp);
            temp = Math.random()*100;
            curfood.setSodium(temp);
            mFood.setSodium(mFood.getSodium()+temp);
            temp = Math.random()*100;
            curfood.setProtein(temp);
            mFood.setProtein(mFood.getProtein()+temp);
            temp = Math.random()*100;
            curfood.setCholesterol(temp);
            mFood.setCholesterol(mFood.getCholesterol()+temp);
            temp = Math.random()*100;
            curfood.setTotal_Carbohydrates(temp);
            mFood.setTotal_Carbohydrates(mFood.getTotal_Carbohydrates()+temp);
        }
        ResultsActivity resultsActivity = new ResultsActivity();
        Class[] cArg = new Class[1];
        cArg[0] = Meal.class;
        Method method = ResultsActivity.class.getDeclaredMethod("sumUp",cArg);
        method.setAccessible(true);
        Food res = (Food) method.invoke(resultsActivity,mMeal);
        double delta = 1e-10;
        assertEquals(res.getName(),mFood.getName());
        assertEquals(res.getCalories(),mFood.getCalories(),delta);
        assertEquals(res.getSodium(),mFood.getSodium(),delta);
        assertEquals(res.getTotal_Fat(),mFood.getTotal_Fat(),delta);
        assertEquals(res.getCholesterol(),mFood.getCholesterol(),delta);
        assertEquals(res.getTotal_Carbohydrates(),mFood.getTotal_Carbohydrates(),delta);
        assertEquals(res.getProtein(),mFood.getProtein(),delta);

    }
}