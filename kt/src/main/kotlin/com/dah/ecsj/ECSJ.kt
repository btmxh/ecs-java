package com.dah.ecsj

typealias CompType = Int

fun compType(engine: Engine<*>, clazz: Class<out Component>): CompType {
    return engine.typeIndexer.next(clazz)
}

operator fun EntityManager.plusAssign(e: Entity) = add(e)
operator fun EntityManager.minusAssign(e: Entity) = remove(e)

operator fun <RD> SystemManager<RD>.plusAssign(es: EntitySystem<RD>) = add(es)
operator fun <RD> SystemManager<RD>.minusAssign(es: EntitySystem<RD>) = remove(es.keyClass)

operator fun FamilyManager.get(f: Family): Collection<Entity> = register(f)

fun Component.ktType(engine: Engine<*>) : CompType = getTypeID(engine)

fun Any?.unit() {}

operator fun Entity.plusAssign(comp: Component) = add(comp).unit()
operator fun Entity.minusAssign(comp: Component) = remove(comp).unit()
operator fun Entity.minusAssign(type: CompType) = remove(type).unit()

operator fun <RD> Engine<RD>.invoke(rd: RD) = run(rd)