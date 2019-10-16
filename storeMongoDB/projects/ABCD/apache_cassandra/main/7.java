        static boolean mapAppliesTo(MapType type, Iterator<Cell> iter, Map<ByteBuffer, ByteBuffer> elements, Operator operator)
        {
            Iterator<Map.Entry<ByteBuffer, ByteBuffer>> conditionIter = elements.entrySet().iterator();
            while(iter.hasNext())
            {
                if (!conditionIter.hasNext())
                    return (operator == Operator.GT) || (operator == Operator.GTE) || (operator == Operator.NEQ);

                Map.Entry<ByteBuffer, ByteBuffer> conditionEntry = conditionIter.next();
                Cell c = iter.next();

                // compare the keys
                int comparison = type.getKeysType().compare(c.path().get(0), conditionEntry.getKey());
                if (comparison != 0)
                    return evaluateComparisonWithOperator(comparison, operator);

                // compare the values
                comparison = type.getValuesType().compare(c.value(), conditionEntry.getValue());
                if (comparison != 0)
                    return evaluateComparisonWithOperator(comparison, operator);
            }

            if (conditionIter.hasNext())
                return (operator == Operator.LT) || (operator == Operator.LTE) || (operator == Operator.NEQ);

            // they're equal
            return operator == Operator.EQ || operator == Operator.LTE || operator == Operator.GTE;
        }
