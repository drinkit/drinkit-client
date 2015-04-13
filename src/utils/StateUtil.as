/**
 * Created by ypoliakov on 29.08.2014.
 */
package utils
{
    import mx.states.State;

    public class StateUtil
    {
        public static function isStateInStateGroup(stateName:String, stateGroup:String, states:Array):Boolean
        {
            var state:State;

            for (var i:int = 0; i < states.length; i++) {
                state = states[i] as State;

                if (state.name == stateName)
                {
                    for (var j:int = 0; j < state.stateGroups.length; j++)
                    {
                        if (state.stateGroups[j] == stateGroup)
                            return true;
                    }

                    return false;
                }
            }

            return false;
        }
    }
}
