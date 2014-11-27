package math;

import java.io.PrintStream;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import java.util.regex.Matcher;

import java.util.regex.Pattern;


import exception.VCalculateException;

/**
 * VFunctor的简单封装可以适应正常方式的书写
 * @author Administrator
 *
 */
public class VEasyFunctor extends VCalculateInterface {
	protected Object[] ungiven = null;
	private VFunctor functor = new VFunctor();
	private String equation_=null;
	public VFunctor getFunctor() {
		return this.functor;
	}
	public VEasyFunctor() {
		
	}
	public void Given(String x, Float v) {
		this.functor.Given(x, v);
	}
	public Float Find() {//old 
		
		return Calc();
	}
	public Float Calc() {
		remind();
		return this.functor.Find();
	}
	public VEasyFunctor Partial(String x) {
		return new VEasyFunctor(this.functor.Partial(x));
	}
	public VEasyFunctor(VFunctor functor) {
		this.functor = functor;
		Equation=changeBack(this.functor.GetEquation(), 1);
		this.ungiven = this.functor.getUnGiven();
		
	}
	public static void main(String[] args) {
		VCalculateInterface vef = new VEasyFunctor();
		VCalculateInterface.init();
		vef.SetEquation("log(x1,2)");
		//vef.SetEquation("pi");
		vef.Given("x1", Float.valueOf(50.0F));
		System.out.println(vef.Calc());
		//System.out.println(vef.getFunctor());
		System.out.println(vef.changeBack(vef.GetEquation(), 1));

		Object[] strsStrings = vef.GetUnGiven();
		System.out.println(strsStrings.length);
		for (Object str : strsStrings)
			System.out.println(str);
	}
	@Override
	public Object[] GetUnGiven() {
		// TODO Auto-generated method stub】
		remind();
		return ungiven;
	}
	private void remind()
	{
		if(!super.Equation.equals(equation_))
		{
			equation_=super.Equation;
			this.functor.SetEquation(changeFrom(super.Equation));
			ungiven=this.functor.getUnGiven();
		}
	}

}

/*
 * Location: E:\EAMath.jar Qualified Name: math.VEasyFunctor JD-Core Version:
 * 0.6.0
 */