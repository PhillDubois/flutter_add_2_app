import 'package:flutter_test/flutter_test.dart';

void main() {
  group(
    'some group',
    () {
      test(
        'some test',
        () {
          expect(1 + 1, 2);
        },
      );
    },
  );
}
