import 'package:flutter/material.dart';

void main() => runApp(const MaterialApp(home: DailyScreen()));

class DailyScreen extends StatelessWidget {
  const DailyScreen({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        body: SafeArea(
          child: Center(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    const SizedBox(
                      width: 100,
                      child: Text('Gate'),
                    ),
                    const Text('A3'),
                  ],
                ),
                Row(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    const Text('Blue'),
                    const SizedBox(
                      width: 100,
                      child: Text('Zone'),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      );
}