package dev.gump.mars.utils;

import java.util.List;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PersistentUtils {
  private static final List<PersistentDataType> PERSISTENT_DATA_TYPE_LIST =
      List.of(
          PersistentDataType.BYTE,
          PersistentDataType.LONG,
          PersistentDataType.DOUBLE,
          PersistentDataType.FLOAT,
          PersistentDataType.SHORT,
          PersistentDataType.STRING,
          PersistentDataType.INTEGER,
          PersistentDataType.TAG_CONTAINER,
          PersistentDataType.BYTE_ARRAY,
          PersistentDataType.INTEGER_ARRAY,
          PersistentDataType.LONG_ARRAY,
          PersistentDataType.TAG_CONTAINER_ARRAY);

  public static PersistentDataType getPersistentDataType(Object value) {
    final var typeName = value.getClass();
    if (typeName.equals(int.class) || typeName.equals(Integer.class))
      return PersistentDataType.INTEGER;
    if (typeName.equals(double.class) || typeName.equals(Double.class))
      return PersistentDataType.DOUBLE;
    if (typeName.equals(float.class) || typeName.equals(Float.class))
      return PersistentDataType.FLOAT;
    if (typeName.equals(long.class) || typeName.equals(Long.class)) return PersistentDataType.LONG;
    if (typeName.equals(byte.class) || typeName.equals(Byte.class)) return PersistentDataType.BYTE;
    if (typeName.equals(short.class) || typeName.equals(Short.class))
      return PersistentDataType.SHORT;
    if (typeName.equals(byte[].class) || typeName.equals(Byte[].class))
      return PersistentDataType.BYTE_ARRAY;
    if (typeName.equals(int[].class) || typeName.equals(Integer[].class))
      return PersistentDataType.INTEGER_ARRAY;
    if (typeName.equals(long[].class) || typeName.equals(Long[].class))
      return PersistentDataType.LONG_ARRAY;
    if (typeName.equals(String.class) || typeName.equals(char.class))
      return PersistentDataType.STRING;
    if (typeName.equals(PersistentDataContainer.class)) return PersistentDataType.TAG_CONTAINER;
    if (typeName.equals(PersistentDataContainer[].class))
      return PersistentDataType.TAG_CONTAINER_ARRAY;
    throw new RuntimeException(String.format("Unsupported type %s on persistent data!", typeName));
  }

  public static PersistentDataType getPersistentDataType(
      PersistentDataContainer container, NamespacedKey key) {
    for (final var dataType : PERSISTENT_DATA_TYPE_LIST) {
      if (container.has(key, dataType)) return dataType;
    }
    return null;
  }
}
