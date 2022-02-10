package de.ubmw.jRetts.lisp;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.lisp.SExpression;
import de.ubmw.jRetts.datalog.Literal;

import java.util.List;
import java.util.function.BiFunction;

import static de.ubmw.jRetts.datalog.Literal.*;

public class EvalUtils {

	public static Literal foldListOfNumbers(
			List<SExpression> params, Env env,
			BiFunction<Double, Double, Double> doubleFn, 
			BiFunction<Long, Long, Long> longFn
			) throws JRettsError {
	
		Boolean isDouble = null;
		Double sDouble = null;
		Long sLong = null;

		for(SExpression s : params) {
			Literal l = s.eval(env);
		
			if (isDouble == null) {
				if(l.isDouble()) {
					isDouble = true;
					sDouble = ((DoubleLit) l).d(); 
				} else {
					isDouble = false;
					sLong = ((LongLit) l).l(); 
				}
			} else {

				//  -- type checker should have made sure, -- //
				//  -- that there'name only double or long -- //
			
				if(isDouble) {
					sDouble = doubleFn.apply(sDouble, ((DoubleLit) l).d());
				} else {
					sLong = longFn.apply(sLong, ((LongLit) l).l());
				}
			}
			
		}
		
		return isDouble ? newDoubleLit(sDouble) : newLongLit(sLong);
	}
}
