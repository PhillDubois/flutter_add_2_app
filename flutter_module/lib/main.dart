import 'package:flutter/material.dart';
import 'package:ionicons/ionicons.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();

  runApp(
    const App(),
  );
}

class App extends StatelessWidget {
  const App({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Column(
          children: [
            Image.asset('assets/flutter_dash.jpeg'),
            Icon(Icons.add),
            Icon(Ionicons.add),
          ],
        ),
      ),
    );
  }
}
