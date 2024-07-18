export type PrependIfDefined<T extends string, S extends string> = T extends "" ? T : `${S}${T}`;

export type Concat<T extends string[], S extends string = '.'> = T extends [
  infer F extends string,
  ...infer R extends string[]
] ? `${F}${PrependIfDefined<Concat<R, S>, S>}` : '';

export type ConstraintType<T, U extends {[key in keyof T]: any}> = {
  [Property in keyof T]: U[Property];
}

export type EventWithNamespace <Namespace extends string, Event extends string> = Concat<[Namespace, Event]>;

type MiddleType<T extends string> = {
  [key in T]: any;
};

/**
 * 为了addEventListener时实现以下功能，此处代码做了特殊处理
 * 1. eventName限定必须是NativeEvent中的类型
 * 2. 用户传入eventName后，ts可以自动识别对应listener类型参数
 *
 * 由于直接interface不能限定里面的key，所以考虑反向限制，即：通过NativeEventListener的报错提示补充Listeners
 * 设计思想：
 * 1. Listeners中定义了所有类型的原生事件及listener的映射关系
 * 2. 定义基础类型MiddleType，限定key必须是NativeEvent中的类型，value不做限定
 * 3. 定义工具类型ConstraintType，该工具类型可以将MiddleType和Listeners整合为一个新类型
 *    a. key是NativeEvent中的一种
 *    b. value是Listeners对应key关联的类型
 *
 * 通过以上方式可实现：如果Listeners类型中的key个数与NativeEvent不匹配时，NativeEventListener报错
 * 测试时，可尝试注释Listeners中的某一行后，NativeEventListener报错
 */
export type NativeEventListenerGeneric<Events extends string, Listeners extends  {[key in Events]: Function}> = ConstraintType<MiddleType<Events>, Listeners>;
