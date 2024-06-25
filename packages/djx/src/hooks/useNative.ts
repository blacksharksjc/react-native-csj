import { useCallback, useRef } from 'react';
import { UIManager, findNodeHandle } from 'react-native';

function useNative(componentName: string) {
  const nativeCompRef = useRef(null);

  const dispatchCommand = useCallback(
    (command: string, args: any[] = []) => {
      const { Commands } = UIManager.getViewManagerConfig(componentName);
      const viewId = findNodeHandle(nativeCompRef.current);
      UIManager.dispatchViewManagerCommand(viewId, String(Commands[command]), [
        viewId,
        ...args,
      ]);
    },
    [componentName]
  );

  return {
    nativeCompRef,
    dispatchCommand,
  };
}

export default useNative;
